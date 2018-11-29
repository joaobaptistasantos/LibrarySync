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

        Utilizadores util = new Utilizadores();
        output = util.emailExiste(input);

        assertFalse(output);
    }
}