package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends Activity {

    Utilizadores utilizadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
    }

    public void exit(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuInicial.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void definicoes(View v){
        Intent intent = new Intent(this, MenuDefinicoes.class);
        intent.putExtra("utilizadores", utilizadores);
        startActivity(intent);
    }
}
