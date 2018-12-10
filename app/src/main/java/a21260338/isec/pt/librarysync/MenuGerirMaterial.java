package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;

public class MenuGerirMaterial extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gerir_material);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");


    }

    public void back(View v){
        finish();
    }

    public void concluido(View v){
        SeekBar canetas = (SeekBar) findViewById(R.id.canetasSeekBar);
        CheckBox Extensao = (CheckBox) findViewById(R.id.checkExtensao);
        CheckBox Apagador = (CheckBox) findViewById(R.id.checkApagador);
        int[] materiais_req = {canetas.getProgress(),Extensao.isChecked() ? 1 : 0 ,Apagador.isChecked() ? 1 : 0};
        Globals.materiais = materiais_req;
        finish();
    }
}
