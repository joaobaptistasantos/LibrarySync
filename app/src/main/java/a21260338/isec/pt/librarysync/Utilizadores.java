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

    List<Utilizador> utilizadores;

    public Utilizadores() {
        utilizadores = new ArrayList<>();

        addSpecialUsers();
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public Utilizador getUtilizador(String email) {
        for(Utilizador e : utilizadores)
            if(e.getEmail().equals(email))
                return e;

        return null;
    }

    public Utilizador addUtilizador(String email, String password, String password2) throws InvalidEmailException, InvalidDifferentPasswordsException {
        validaDados(email, password, password2);

        Utilizador novo = new Aluno(email, password);
        utilizadores.add(novo);
        return novo;
    }

    public boolean removeUtilizador(Utilizador user){
        try{
            Utilizador remover = null;

            for(Utilizador u : utilizadores)
                if(u.getEmail().equals(user.email))
                    remover = u;

            utilizadores.remove(remover);

            return utilizadores.remove(remover);
        } catch(NullPointerException e){
            Log.d("Useres", "Falhou eliminar utilizador");
            return false;
        }
    }

    public boolean emailExiste(String email)
    {
        for(Utilizador u : utilizadores)
            if(u.getEmail().equals(email))
                return true;

        return false;
    }

    public void validaDados(String email) throws InvalidEmailException {
        if(!email.contains("@"))
            throw new InvalidEmailException("Email Inválido");
    }

    public void validaDados(String email, String passsword) throws InvalidEmailException {
        validaDados(email);

        // falta validar passwords e emails em termos de string inserida
    }

    public void validaDados(String email, String password, String password2) throws InvalidEmailException, InvalidDifferentPasswordsException {
        validaDados(email);

        if(!password.equals(password2))
            throw new InvalidDifferentPasswordsException("Passwords têm que ser iguais!");

        // falta validar passwords e emails em termos de string inserida
    }

    public Utilizador autentica(String email, String password) throws InvalidEmailException, InvalidAuthenticationException{
        validaDados(email, password);

        for(Utilizador u : utilizadores)
            if(u.autentica(email, password))
                return u;

        throw new InvalidAuthenticationException("Dados inválidos!");
    }

    public Intent recuperarConta(String email) throws InvalidEmailException, InvalidAccountRecover {
        validaDados(email);

        String subject = "Recupera password LibrarySync";

        for (Utilizador u: utilizadores) {
            if(email.equals(u.getEmail())) {
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

    public void addSpecialUsers(){
        utilizadores.add(new Docente("docente@isec.pt", "docente"));
        utilizadores.add(new Recepcionista("recepcionista@isec.pt", "recepcionista"));
    }

    public void mudarPassord(Utilizador user, String passwordAntiga, String passwordNova, String passwordNova2) throws InvalidDifferentPasswordsException {
        //validaPassword();

        if(!(user.getPassword().equals(passwordAntiga)))
            throw new InvalidDifferentPasswordsException("Password atual errada!");

        if(!passwordNova.equals(passwordNova2))
            throw new InvalidDifferentPasswordsException("Passwords têm que ser iguais!");

        for(Utilizador u : utilizadores)
            if(u.getEmail().equals(user.email))
                u.setPassword(passwordNova);

        user.setPassword(passwordNova);
    }
}