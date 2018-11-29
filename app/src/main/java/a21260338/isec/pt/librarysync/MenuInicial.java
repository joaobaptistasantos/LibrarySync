package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.InvalidParameterException;

public class MenuInicial extends Activity {

    Utilizadores utilizadores;
    Utilizador ativo;
    TextView msgErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        msgErro = (TextView) findViewById(R.id.erroMenuInicial);

        utilizadores = new Utilizadores();
        ativo = null;

        FileInputStream fIn = null;

        try{
            fIn = openFileInput("logs52.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            String[] dados = null;

            while((text = br.readLine()) != null){
                sb.append(text).append("\n");
                dados = sb.toString().split(" ");
            }

            for(int i = 0; i < dados.length - 1; i+=2){
                try {
                    utilizadores.addUtilizador(dados[i], dados[i + 1], dados[i + 1]);
                } catch(InvalidEmailException e){
                } catch(InvalidDifferentPasswordsException e){
                }
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fIn != null){
                try{
                    fIn.close();
                } catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        intent.putExtra("utilizadores",(Serializable) utilizadores);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            utilizadores = (Utilizadores) data.getSerializableExtra("utilizadores");
        }
    }

    public void esqueceuPassword(View v){
        Intent intent = new Intent(this, MenuEsqueceuPassword.class);
        intent.putExtra("utilizadores",(Serializable) utilizadores.getUtilizadores());
        startActivity(intent);
    }

    public void login(View v){
        EditText et = (EditText)findViewById(R.id.emailInput_MenuInicial);
        String email = et.getText().toString();

        et = (EditText)findViewById(R.id.passwordInput_MenuInicial);
        String password = et.getText().toString();

        try{
            ativo = utilizadores.autentica(email, password);

            Intent intent = null;

            if(ativo.getClass() == Aluno.class)
                intent = new Intent(this, MenuPrincipal.class);
            else if(ativo.getClass() == Docente.class)
                intent = new Intent(this, MenuPrincipalDocente.class);
            else if(ativo.getClass() == Recepcionista.class)
                intent = new Intent(this, MenuPrincipalRecepcionista.class);

            intent.putExtra("utilizadores", utilizadores);
            intent.putExtra("ativo", ativo);
            startActivity(intent);

        } catch(InvalidEmailException e){
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
        } catch(InvalidAuthenticationException e){
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
        }
    }
}