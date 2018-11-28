package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MenuInicial extends Activity {

    Utilizadores utilizadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inical);

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("ListaUtilizadores", Context.MODE_PRIVATE);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File listaUtilizadores = new File(this.getFilesDir(), "ListaUtilizadores");

        try {
            utilizadores = new Utilizadores(listaUtilizadores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("Useres", "Tamanho: "+String.valueOf(utilizadores.utilizadores.size()));
    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        intent.putExtra("utilizadores", utilizadores);
        startActivity(intent);
    }

    public void esqueceuPassword(View v){
        Intent intent = new Intent(this, MenuEsqueceuPassword.class);
        startActivity(intent);
    }

    public void login(View v){
        String[]dados = {"", ""};

        EditText et = (EditText)findViewById(R.id.emailInput_MenuInicial);
        dados[0] = et.getText().toString();

        et = (EditText)findViewById(R.id.passwordInput_MenuInicial);
        dados[1] = et.getText().toString();

        if(verificaLogin(dados)==true){
            Intent intent = new Intent(this, MenuPrincipal.class);
            intent.putExtra("utilizadores", utilizadores);
            startActivity(intent);
        }

        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    public boolean verificaLogin(String[] dados){
        for (Utilizador u: utilizadores.getUtilizadores()) {
            if(u.getEmail().equals(dados[0]) == true){
                if(u.getPassword().equals(dados[1]) == true){
                    return true;
                }
            }
        }

        return false;
    }
}
