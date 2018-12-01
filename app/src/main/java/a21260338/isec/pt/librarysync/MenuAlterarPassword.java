package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class MenuAlterarPassword extends Activity {

    TextView msgErro;
    Utilizadores utilizadores;
    Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alterar_password);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
    }

    public void back(View v) { finish(); }

    public void alterar(View v){
        EditText input = (EditText) findViewById(R.id.passAntigaInput_MenuAlterarPassword);
        String passAtual = input.getText().toString();

        input = (EditText) findViewById(R.id.passNovaInput_MenuAlterarPassword);
        String passNova = input.getText().toString();

        input = (EditText) findViewById(R.id.confirmPassInput_MenuAlterarPassword);
        String passNova2 = input.getText().toString();

        msgErro = (TextView) findViewById(R.id.erroMudarPass);

        try{
            utilizadores.mudarPassord(ativo, passAtual, passNova, passNova2);
        } catch(InvalidDifferentPasswordsException e) {
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
            return;
        }

        FileOutputStream fOut = null;

        try {
            fOut = openFileOutput("logs54.txt", MODE_PRIVATE);

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
    }
}
