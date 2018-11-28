package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.security.InvalidParameterException;
import java.util.List;

public class MenuEsqueceuPassword extends Activity {

    //Utilizadores utilizadores;
    List<Utilizador> users;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_esqueceu_password);

        users = (List<Utilizador>) getIntent().getSerializableExtra("utilizadores");

        et = findViewById(R.id.emailInput_MenuEsqueceu);
    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        startActivity(intent);
    }

    public void onLogin(View v){
        Intent intent = new Intent(this, MenuInicial.class);
        startActivity(intent);
    }

    public void enviarEmail(View v){
        try {
            String subject = "Recupera password LibrarySync";

            for (Utilizador u: users) {
                if(et.getText().toString().equals(u.getEmail())){
                    String[] enviarPara = {et.getText().toString()};
                    String password = u.getPassword();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, enviarPara);
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    intent.putExtra(Intent.EXTRA_TEXT, password);

                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Escolher cliente"));
                }
            }
        }catch (InvalidParameterException e){
            Log.d("Useres", "Sem utilizadores registados");
        }

    }
}
