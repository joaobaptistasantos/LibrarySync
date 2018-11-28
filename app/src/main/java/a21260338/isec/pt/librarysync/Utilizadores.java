package a21260338.isec.pt.librarysync;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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

    public boolean addUtilizador(String[] dados, File diretorio) throws IOException {

        if(utilizadores != null){
            for (Utilizador u: utilizadores) {
                if(u.getEmail()==dados[0]){
                    return false;
                }
            }
        }

        utilizadores.add(new Utilizador(dados[0], dados[1]));

        return true;
    }
}