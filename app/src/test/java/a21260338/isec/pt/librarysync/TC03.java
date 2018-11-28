package a21260338.isec.pt.librarysync;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

public class TC03 {
    @Test
    public void emailExiste() throws FileNotFoundException {
        String input = "algo@algo.pt";
        //boolean expected = true;
        boolean output;
        //double delta = 0.1;

        FileOutputStream out = new FileOutputStream("ListaUtilizadores");

        File listaUtilizadores = new File("ListaUtilizadores");

        Utilizadores util = new Utilizadores(listaUtilizadores);
        output = util.emailExiste(input);

        assertFalse(output);
    }
}