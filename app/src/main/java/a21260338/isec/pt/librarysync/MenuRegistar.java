package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuRegistar extends Activity {

    Utilizadores utilizadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registar);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
    }

    public void onLogin(View v){
        Intent intent = new Intent(this, MenuInicial.class);
        startActivity(intent);
    }

    public void registar(View v) throws IOException {
        EditText et = (EditText)findViewById(R.id.emailInput_MenuRegistar);
        String email = et.getText().toString();

        et = (EditText)findViewById(R.id.passwordInput_MenuRegistar);
        String password = et.getText().toString();

        et = (EditText)findViewById(R.id.confirmPasswordInput);
        String passwordConfirmacao = et.getText().toString();

        utilizadores.addUtilizador(email, password, passwordConfirmacao);

        String result = email + " " + password + " ";

        FileOutputStream fOut = null;

        try {
            fOut = openFileOutput("logs50.txt", MODE_APPEND);
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

        Intent intent = new Intent();
        intent.putExtra("utilizadores", utilizadores);
        setResult(1, intent);

        finish();
    }
}
