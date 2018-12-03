package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MenuEstatisticas extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_estatisticas);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
    }

    public void back(View v){
        finish();
    }
}
