package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.List;

public class MenuEsqueceuPassword extends Activity {

    Utilizadores utilizadores;
    List<Utilizador> users;
    private EditText et;
    TextView msgErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_esqueceu_password);

        msgErro = (TextView) findViewById(R.id.erroMenuEsqueceuPassword);
        et = findViewById(R.id.emailInput_MenuEsqueceu);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        intent.putExtra("utilizadores",(Serializable) utilizadores);
        startActivity(intent);
    }

    public void onLogin(View v){
        finish();
    }

    public void enviarEmail(View v){
        try {
            String emailTo = et.getText().toString();
            Intent intent = utilizadores.recuperarConta(emailTo);

            startActivity(Intent.createChooser(intent, "Escolher cliente"));
        }catch (InvalidEmailException e){
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
            return;
        } catch(InvalidAccountRecover e){
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
            return;
        }

        onLogin(v);
    }
}