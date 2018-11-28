package a21260338.isec.pt.librarysync;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilizadores implements Serializable {

    List<Utilizador> utilizadores;

    public Utilizadores(File listaUtilizadores) throws FileNotFoundException {
        Scanner sc = new Scanner(listaUtilizadores);
        String[] dados;

        utilizadores = new ArrayList<Utilizador>();

        while (sc.hasNext()){
            dados = sc.next().split(" ");
            utilizadores.add(new Utilizador(dados[0], dados[1]));
        }
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public boolean addUtilizador(String email, String password, String password2) throws IOException, InvalidObjectException {

        try{
            validaDados(email, password, password2);

            utilizadores.add(new Utilizador(email, password));
        } catch (InvalidParameterException e){
            Log.d("Useres", e.toString());
        }

        return true;
    }

    public void logUtilizadores(){
        for(Utilizador u : utilizadores)
            Log.d("Useres", "Email: " + u.getEmail() + " Password: " + u.getPassword());
    }

    public void validaDados(String email, String password, String password2){
        if(!email.contains("@"))
            throw new InvalidParameterException("Email Inválido");

        if(!password.equals(password2))
            throw new InvalidParameterException("Passwords têm que ser iguais!");

        for(Utilizador u : utilizadores)
            if(u.getEmail().equals(email))
                throw new InvalidParameterException("Email já existe");

        // falta validar passwords e emails em termos de string inserida
    }
}