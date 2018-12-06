package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

import static a21260338.isec.pt.librarysync.Globals.filename;

public class MenuAlterarPassword extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

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

        try{
            utilizadores.MudarPassord(ativo, passAtual, passNova, passNova2);
        } catch(InvalidDifferentPasswordsException | InvalidPasswordException e) {
            TextView msgErro = (TextView) findViewById(R.id.erroMudarPass);
            msgErro.setText(e.getMessage());
            msgErro.setVisibility(View.VISIBLE);
            return;
        } catch(Exception e){
            e.printStackTrace();
        }

        FileOutputStream fOut = null;

        try {
            fOut = openFileOutput(filename, MODE_PRIVATE);

            for (Utilizador u : utilizadores.GetListData()) {
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

        finish();
    }
}