package a21260338.isec.pt.librarysync;

import android.content.Intent;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

//TC01
public class UnitTest_Utilizadores {

    Utilizadores testUsers = new Utilizadores(new ArrayList<Utilizador>());
    Aluno testUser1, testUser2, testUser3;

    //TC01.1
    private void TestAddUtilizador(){
        try {
            testUser1 = testUsers.AddUtilizador("email@email.com1", "password1", "password1");
            testUser2 = testUsers.AddUtilizador("email@email.com2", "password2", "password2");
            testUser3 = testUsers.AddUtilizador("email@email.com3", "password3", "password3");

            assertTrue(testUser1.equals(new Aluno("email@email.com1","password1",5)));
            assertTrue(testUser2.equals(new Aluno("email@email.com2","password2",5)));
            assertTrue(testUser3.equals(new Aluno("email@email.com3","password3",5)));
        }catch (Exception ex){
            System.out.println(ex.toString());
        }
    }
    //TC01.1
    public void TesteMudarPassword(){
        try {
            assertTrue(testUsers.MudarPassord(testUser1, "password1", "passwordX1", "passwordX1"));
            assertFalse(testUsers.MudarPassord(testUser2, "password1", "passwordX2", "passwordX2"));
            assertTrue(testUsers.MudarPassord(testUser3, "password3", "passwordX3", "passwordX3"));

            assertTrue(testUser1.equals(new Aluno("email@email.com1","passwordX1",5)));
            assertFalse(testUser2.equals(new Aluno("email@email.com2","passwordX2",5)));
            assertTrue(testUser3.equals(new Aluno("email@email.com3","passwordX3",5)));
        }catch (Exception ex)  {
            System.out.println(ex.toString());
        }
    }

    //Tests all methods in class "Utilizadores"
    @Test
    public void TestUtilizadores() {
        TestAddUtilizador();
        TesteMudarPassword();
    }
}
