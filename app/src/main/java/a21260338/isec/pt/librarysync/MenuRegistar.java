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

        Intent intent = new Intent(this, MenuInicial.class);
        intent.putExtra("utilizadores", utilizadores);
        startActivity(intent);
    }
}
