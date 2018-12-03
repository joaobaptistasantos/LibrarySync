package a21260338.isec.pt.librarysync;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilizadores implements Serializable {

    private List<Utilizador> utilizadores;

    public Utilizadores() {
        utilizadores = new ArrayList<>();

        addSpecialUsers();
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public Utilizador addUtilizador(String email, String password, String password2) throws InvalidEmailException, InvalidDifferentPasswordsException, InvalidPasswordException, AccountAlreadyExistsException {
        emailExiste(email);
        validaEmail(email);
        validaPassword(password);

        if(!password.equals(password2))
            throw new InvalidDifferentPasswordsException("Passwords têm que ser iguais!");

        Utilizador novo = new Aluno(email, password);
        utilizadores.add(novo);
        return novo;
    }

    public boolean removeUtilizador(Utilizador user){
        try{
            String email = user.getEmail();
            Utilizador remover = null;

            for(Utilizador u : utilizadores)
                if(u.emailCorreto(email))
                    remover = u;

            utilizadores.remove(remover);

            return utilizadores.remove(remover);
        } catch(NullPointerException e){
            return false;
        }
    }

    public void emailExiste(String email) throws AccountAlreadyExistsException {
        for(Utilizador u : utilizadores)
            if(u.emailCorreto(email))
                throw new AccountAlreadyExistsException("Conta já existente!");
    }

    public void validaEmail(String email) throws InvalidEmailException {
        if(!email.contains("@") || !Character.isAlphabetic(email.charAt(email.length()-1)) || !Character.isAlphabetic(email.charAt(0)))
            throw new InvalidEmailException("Email Inválido");
    }

    public void validaPassword(String password) throws InvalidPasswordException {
        if(password.isEmpty())
            throw new InvalidPasswordException("Password por preencher!");

        if(!password.matches("[a-zA-Z0-9]*"))
            throw new InvalidPasswordException("Password deve conter apenas letras e números!");
    }

    public Utilizador autentica(String email, String password) throws InvalidEmailException, InvalidPasswordException, InvalidAuthenticationException{
        validaEmail(email);
        validaPassword(password);

        for(Utilizador u : utilizadores)
            if(u.autentica(email, password))
                return u;

        throw new InvalidAuthenticationException("Conta não existe!");
    }

    public Intent recuperarConta(String email) throws InvalidEmailException, InvalidAccountRecover {
        validaEmail(email);

        String subject = "Recupera password LibrarySync";

        for (Utilizador u: utilizadores) {
            if(u.emailCorreto(email)) {
                String password = u.getPassword();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, password);
                intent.setType("message/rfc822");

                return intent;
            }
        }

        throw new InvalidAccountRecover("Conta impossível de recuperar!");
    }

    public void mudarPassord(Utilizador user, String passwordAntiga, String passwordNova, String passwordNova2) throws InvalidDifferentPasswordsException, InvalidPasswordException {
        if(!(user.passwordCorreta(passwordAntiga)))
            throw new InvalidDifferentPasswordsException("Password atual errada!");

        if(!passwordNova.equals(passwordNova2))
            throw new InvalidDifferentPasswordsException("Passwords têm que ser iguais!");

        validaPassword(passwordNova);

        String email = user.getEmail();

        for(Utilizador u : utilizadores)
            if(u.emailCorreto(email)) {
                u.setPassword(passwordNova);
                user.setPassword(passwordNova);
            }
    }

    public void addSpecialUsers(){
        utilizadores.add(new Docente("docente@isec.pt", "docente"));
        utilizadores.add(new Recepcionista("recepcionista@isec.pt", "recepcionista"));
    }
}