package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import static a21260338.isec.pt.librarysync.Globals.filename;

public class MenuRegistar extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registar);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = null;
    }

    public void onLogin(View v){
        Intent intent = new Intent();
        intent.putExtra("utilizadores", utilizadores);
        setResult(1, intent);
        finish();
    }

    public void registar(View v) throws IOException {
        EditText et = (EditText)findViewById(R.id.emailInput_MenuRegistar);
        String email = et.getText().toString().trim();

        et = (EditText)findViewById(R.id.passwordInput_MenuRegistar);
        String password = et.getText().toString();

        et = (EditText)findViewById(R.id.confirmPasswordInput);
        String passwordConfirmacao = et.getText().toString();

        try {
            ativo = utilizadores.AddUtilizador(email, password, passwordConfirmacao);
        } catch(InvalidEmailException | InvalidDifferentPasswordsException | InvalidPasswordException | AccountAlreadyExistsException e){
            TextView msgErro = (TextView) findViewById(R.id.erroMenuRegistar);
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
            return;
        } catch(Exception e){
            e.printStackTrace();
            return;
        }

        String result = email + " " + password + " ";
        FileOutputStream fOut = null;

        try {
            fOut = openFileOutput(filename, MODE_APPEND);
            fOut.write(result.getBytes());
            fOut.flush();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
        finally {
            if(fOut != null){
                try{
                    fOut.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        Intent intent = new Intent(this, MenuPrincipal.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Intent intent = new Intent();
        setResult(0, intent);
        finish();
    }
}