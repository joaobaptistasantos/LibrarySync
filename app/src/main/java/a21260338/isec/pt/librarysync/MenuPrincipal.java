package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class MenuPrincipal extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;
    private Reservas reservas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
        //reservas = (Reservas) getIntent().getSerializableExtra("reservas");
        reservas = new Reservas();

        TextView tvEmailUser = (TextView) findViewById(R.id.emailUser_MenuPrincipal);
        tvEmailUser.setText(ativo.getEmail());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3 && resultCode == 1){
            reservas = (Reservas) data.getSerializableExtra("reservas");
            Log.i("Useres", "Tamanho : " + reservas.getReservas().size());
        }
    }

    public void reservarGabinete(View v){
        Intent intent = new Intent(this, MenuReservarGabinete.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        intent.putExtra("reservas", reservas);
        startActivityForResult(intent, 3);
    }

    public void gerirReservas(View v){
        // meter o codigo abaixo quando a tabela for feita
        // Intent intent = new Intent(this, MenuGerirReservas.class);
        Intent intent = new Intent(this, MenuGerirReservas.class);
        intent.putExtra("ativo", ativo);
        intent.putExtra("reservas", reservas);
        startActivity(intent);
    }

    public void exit(View v) {
        Intent intent = new Intent(getApplicationContext(), MenuInicial.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void definicoes(View v){
        Intent intent = new Intent(this, MenuDefinicoes.class);
        intent.putExtra("utilizadores", utilizadores);
        intent.putExtra("ativo", ativo);
        startActivity(intent);
    }
}