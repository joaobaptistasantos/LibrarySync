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

    private Utilizadores utilizadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_esqueceu_password);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
    }

    public void onRegistar(View v){
        Intent intent = new Intent();
        intent.putExtra("utilizadores", utilizadores);
        setResult(1, intent);
        finish();
    }

    public void onLogin(View v){
        finish();
    }

    public void enviarEmail(View v){
        try {
            EditText et = findViewById(R.id.emailInput_MenuEsqueceu);
            String emailTo = et.getText().toString().trim();
            Intent intent = utilizadores.recuperarConta(emailTo);

            startActivity(Intent.createChooser(intent, "Escolher cliente"));
        }catch (InvalidEmailException | InvalidAccountRecover e){
            TextView msgErro = (TextView) findViewById(R.id.erroMenuEsqueceuPassword);
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
            return;
        } catch(Exception e){
            e.printStackTrace();
        }

        onLogin(v);
    }
}