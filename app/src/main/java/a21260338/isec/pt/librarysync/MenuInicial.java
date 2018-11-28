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
import java.io.Serializable;
import java.security.InvalidParameterException;

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

        try{
            utilizadores.addUtilizador("jocatoca3@gmail.com", "aa", "aa");
        }catch (Exception e)
        {

        }

    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        intent.putExtra("utilizadores",(Serializable) utilizadores);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            utilizadores = (Utilizadores) data.getSerializableExtra("utilizadores");
        }
    }

    public void esqueceuPassword(View v){
        Intent intent = new Intent(this, MenuEsqueceuPassword.class);
        intent.putExtra("utilizadores",(Serializable) utilizadores.getUtilizadores());

        startActivity(intent);
    }

    public void login(View v){
        EditText et = (EditText)findViewById(R.id.emailInput_MenuInicial);
        String email = et.getText().toString();

        et = (EditText)findViewById(R.id.passwordInput_MenuInicial);
        String password = et.getText().toString();

        try{
            utilizadores.autentica(email, password);

            Intent intent = new Intent(this, MenuPrincipal.class);
            startActivity(intent);

        } catch(InvalidParameterException e){
            Log.d("Useres", "Falhou login");
        }
    }
}
