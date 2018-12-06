package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MenuGerirReservas extends Activity {

    private Utilizador ativo;
    private Reservas reservas;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gerir_reservas);

        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
        reservas = (Reservas) getIntent().getSerializableExtra("reservas");

        tv = (TextView) findViewById(R.id.TVgerirReservas);
        tv.setText("");
        for (Reserva r: reservas.getReservas()) {
            if(r.getResponsavel().getEmail().equals(ativo.getEmail())){
                tv.append("\nGabinete: " + r.getGabinete().getNrGabinete());
                tv.append("\nResponsável: " + r.getResponsavel().getEmail());
                tv.append("\nHora Início: " + r.getHoraInicio().toString());
                tv.append("\nHora Fim: " + r.getHoraFim().toString());
            }
            tv.append("\n");
        }
    }

    public void back(View v){
        finish();
    }
}
