package a21260338.isec.pt.librarysync;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

public class TC01 {

    @Test
    public void validaDados() throws FileNotFoundException {
        String input = "algo@algo.pt";
        boolean output;

        FileOutputStream out = new FileOutputStream("ListaUtilizadores");

        File listaUtilizadores = new File("ListaUtilizadores");

        Utilizadores util = new Utilizadores(listaUtilizadores);
        // validadeDados não é boolean
        output = util.validaDados(input);

        assertTrue(output);

    }
}