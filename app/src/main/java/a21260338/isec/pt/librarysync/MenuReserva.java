package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuReserva extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;

    // É o mesmo menu reserva do utilizador mas tem dois campos com visibility a gone (responsavel)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reserva);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
    }

    public void back(View v){
        finish();
    }

    public void gerirOcupantes(View v){
        Intent intent = new Intent(this, MenuGerirOcupantes.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        startActivity(intent);
    }

    public void gerirMaterial(View v){
        Intent intent = new Intent(this, MenuGerirMaterial.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        startActivity(intent);
    }
}
