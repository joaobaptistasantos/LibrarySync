package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

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
        String[]dados = {"", ""};

        EditText et = (EditText)findViewById(R.id.emailInput_MenuRegistar);
        dados[0] = et.getText().toString();

        et = (EditText)findViewById(R.id.passwordInput_MenuRegistar);
        dados[1] = et.getText().toString();

        et = (EditText)findViewById(R.id.confirmPasswordInput);
        String passwordConfirmacao = et.getText().toString();

        if(dados[0]=="" || dados[1]==""){
            return;
        }

        if(passwordConfirmacao.equals(dados[1]) == false){
            return;
        }

        utilizadores.addUtilizador(dados, this.getFilesDir());

        Intent intent = new Intent(this, MenuInicial.class);
        startActivity(intent);
    }
}
