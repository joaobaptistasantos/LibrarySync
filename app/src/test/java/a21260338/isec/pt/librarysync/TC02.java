package a21260338.isec.pt.librarysync;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

public class TC02 {

    @Test
    public void autentica() throws FileNotFoundException, FileNotFoundException {
        String input1 = "      ";
        String input2 = "1234";
        boolean output = false;
        Utilizador teste = null;

        Utilizadores util = new Utilizadores();
        teste = util.autentica(input1, input2);

        output = teste == null ? false : true;

        assertFalse(output);

    }
}