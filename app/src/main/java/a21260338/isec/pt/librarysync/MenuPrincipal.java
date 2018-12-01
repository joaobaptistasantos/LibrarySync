package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MenuPrincipal extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");

        TextView tvEmailUser = (TextView) findViewById(R.id.emailUser_MenuPrincipal);
        tvEmailUser.setText(ativo.getEmail());
    }

    public void exit(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuInicial.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void definicoes(View v){
        Intent intent = new Intent(this, MenuDefinicoes.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        startActivity(intent);
    }
}