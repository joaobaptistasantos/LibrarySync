package a21260338.isec.pt.librarysync;

import android.app.Activity;
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
import java.util.ArrayList;

import static a21260338.isec.pt.librarysync.Globals.filename;

public class MenuInicial extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        utilizadores = new Utilizadores(new ArrayList<Utilizador>());
        ativo = null;

        FileInputStream fIn = null;

        try{
            fIn = openFileInput(filename);
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
                    Log.d("TestPass", "Username: " + dados[i]);
                    Log.d("TestPass", "Password: " + dados[i+1]);
                    utilizadores.AddUtilizador(dados[i], dados[i + 1], dados[i + 1]);
                } catch(InvalidEmailException | InvalidDifferentPasswordsException | InvalidPasswordException | AccountAlreadyExistsException e){
                } catch(Exception e){
                    e.printStackTrace();
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
        intent.putExtra("utilizadores",(Serializable) utilizadores);
        startActivity(intent);
    }

    public void consultarHorariosDisponiveis(View v){
        Intent intent = new Intent(this, MenuConsultarHorariosDisponiveis.class);
        startActivity(intent);
    }

    public void login(View v){
        EditText et = findViewById(R.id.emailInput_MenuInicial);
        String email = et.getText().toString().trim();

        et = findViewById(R.id.passwordInput_MenuInicial);
        String password = et.getText().toString();

        try{
            ativo = utilizadores.Autentica(email, password);

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

        } catch(InvalidAuthenticationException e){
            TextView msgErro = (TextView) findViewById(R.id.erroMenuInicial);
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}