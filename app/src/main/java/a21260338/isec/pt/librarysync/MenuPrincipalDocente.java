package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class MenuPrincipalDocente extends Activity {

    private AlertDialog sobre;
    Utilizadores utilizadores;
    Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_docente);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");

        Log.d("Useres", "Ativo: " + ativo.getEmail());
    }

    public void exit(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuInicial.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void sobre(View v){
        // LayoutInflater é utilizado para inflar o layout numa view
        // Pegamos nessa instancia da classe
        LayoutInflater li = getLayoutInflater();

        // Inflamos o layout sobre.xml na view
        View view = li.inflate(R.layout.sobre, null);
        // Definimos para o botão do layout um clickListener
        view.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Desfaz o alerta
                sobre.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sobre")
                .setView(view);

        sobre = builder.create();
        sobre.show();
    }
}
