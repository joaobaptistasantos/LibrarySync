package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static a21260338.isec.pt.librarysync.Globals.filename;

public class MenuDefinicoes extends Activity {

    private AlertDialog dialog;
    private Utilizadores utilizadores;
    private Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_definicoes);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
    }

    public void back(View v){
        finish();
    }

    public void mudarPassword(View b){
        Intent intent = new Intent(this, MenuAlterarPassword.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        startActivity(intent);
    }

    public void cancelarConta(View v){
        // Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Aviso")
                .setMessage("Tem a certeza que pretende cancelar a sua conta?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        utilizadores.removeUtilizador(ativo);

                        FileOutputStream fOut = null;

                        try {
                            fOut = openFileOutput(filename, MODE_PRIVATE);

                            for (Utilizador u : utilizadores.getUtilizadores()) {
                                String result = u.getEmail() + " " + u.getPassword() + " ";
                                fOut.write(result.getBytes());
                                fOut.flush();
                            }
                        }
                        catch (IOException e) {
                            Log.e("Exception", "File write failed: " + e.toString());
                        }
                        finally {
                            if(fOut != null){
                                try{
                                    fOut.close();
                                } catch(IOException e){
                                    e.printStackTrace();
                                }
                            }
                        }

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
        dialog = builder.create();
        //Exibe
        dialog.show();
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
                dialog.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sobre")
                .setView(view);

        dialog = builder.create();
        dialog.show();
    }
}
