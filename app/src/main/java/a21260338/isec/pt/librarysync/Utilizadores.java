package a21260338.isec.pt.librarysync;

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

    public boolean addUtilizador(String email, String password, String password2) throws InvalidEmailException, InvalidDifferentPasswordsException {

        try{
            validaDados(email, password, password2);

            utilizadores.add(new Aluno(email, password));
        } catch (InvalidParameterException e){
            Log.d("Useres", e.toString());
        }

        return true;
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

    public void addSpecialUsers(){
        utilizadores.add(new Docente("docente@isec.pt", "docente"));
        utilizadores.add(new Recepcionista("recepcionista@isec.pt", "recepcionista"));
    }
}