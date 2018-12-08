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
        canetas.getProgress();
        CheckBox Apagador = (CheckBox) findViewById(R.id.checkApagador);
        Apagador.isSelected();
        CheckBox Extensao = (CheckBox) findViewById(R.id.checkExtensao);
        Extensao.isSelected();
        int[] materiais = {canetas.getProgress(),Extensao.isSelected() ? 1 : 0,Apagador.isSelected() ? 1 : 0};
        Intent intent = new Intent(getBaseContext(), MenuReservarGabinete.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        intent.putExtra("materiais", materiais);
        startActivity(intent);
        //finish();
    }
}
