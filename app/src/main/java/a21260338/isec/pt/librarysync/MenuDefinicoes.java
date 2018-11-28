package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MenuDefinicoes extends Activity {

    private AlertDialog sobre;
    private AlertDialog aviso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_definicoes);
    }

    public void back(View v){
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    public void cancelarConta(View v){
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Aviso");
        //define a mensagem
        builder.setMessage("Tem a certeza que pretende cancelar a sua conta?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // para implementar
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                // para implementar
            }
        });

        //cria o AlertDialog
        aviso = builder.create();
        //Exibe
        aviso.show();
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
