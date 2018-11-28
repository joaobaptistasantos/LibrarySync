package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.nio.file.SecureDirectoryStream;

public class MenuPrincipal extends Activity {

    Utilizadores utilizadores;
    String userOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        userOnline = getIntent().getStringExtra("userOnline");
    }

    public void exit(View v) {
        Intent intent = new Intent();
        intent.putExtra("utilizadores", utilizadores);
        setResult(1, intent);
        finish();
    }

    public void definicoes(View v){
        Intent intent = new Intent(this, MenuDefinicoes.class);
        intent.putExtra("utilizadores",(Serializable) utilizadores);
        intent.putExtra("userOnline", userOnline);
        startActivity(intent);
    }
}
