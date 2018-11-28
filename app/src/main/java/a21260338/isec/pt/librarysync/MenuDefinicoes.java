package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MenuDefinicoes extends Activity {

    private AlertDialog sobre;
    private AlertDialog aviso;
    Utilizadores utilizadores;
    Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_definicoes);

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("ListaUtilizadores", Context.MODE_PRIVATE);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File listaUtilizadores = new File(this.getFilesDir(), "ListaUtilizadores");

        try {
            utilizadores = new Utilizadores(listaUtilizadores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            utilizadores.addUtilizador("jocatoca3@gmail.com", "aa", "aa");
            utilizadores.addUtilizador("joca_toca@hotmail.com", "bb", "bb");
            ativo = utilizadores.getUtilizador("jocatoca3@gmail.com");
        }catch (Exception e)
        {

        }
    }

    public void back(View v){
        finish();
    }

    public void cancelarConta(View v){
        // Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Aviso")
                .setMessage("Tem a certeza que pretende cancelar a sua conta?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        utilizadores.logUtilizadores();

                        utilizadores.removeUtilizador(ativo);

                        utilizadores.logUtilizadores();

                        Intent intent = new Intent(getApplicationContext(), MenuInicial.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        // nao faz acao nenhuma
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
