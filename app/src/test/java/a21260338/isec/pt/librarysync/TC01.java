package a21260338.isec.pt.librarysync;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class TC01 {

    @Test
    public void validaDados() throws FileNotFoundException {
        String input = "algo@algo.pt";
        boolean output = true;

        Utilizadores util = new Utilizadores();
        // validadeDados não é boolean
        try {
            util.validaDados(input);
        } catch(InvalidParameterException e){
            output = false;
        }

        assertTrue(output);
    }
}