package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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


        for (Reserva r: reservas.getReservas()) {
            if(tv.getText().equals("Sem reservas em seu nome")==true){
                tv.setMaxLines(0);
                tv.setText("");
            }
            tv.setMaxLines(tv.getMaxLines() + 7);
            if(r.getResponsavel().getEmail().equals(ativo.getEmail())){
                tv.append("\nGabinete: " + r.getGabinete().getNrGabinete());
                tv.append("\nResponsável: " + r.getResponsavel().getEmail());
                tv.append("\nHora Início: " + r.getHoraInicio().toString());
                tv.append("\nHora Fim: " + r.getHoraFim().toString());
                tv.append("\nData Reserva: " + r.getDataReserva().getDate() + "/" + (r.getDataReserva().getMonth()+1) + "/" + r.getDataReserva().getYear());
                tv.append("\nMateriais: " + r.getNumMateriais());
                tv.append(("\n->Canetas: "+ r.getNumMaterialByIndex(0)));
                tv.append(("\n->Extensão: "+ r.getNumMaterialByIndex(1)));
                tv.append(("\n->Apagador: "+ r.getNumMaterialByIndex(2)));

            }
            tv.append("\n");
        }
        tv.setMovementMethod(new ScrollingMovementMethod());
    }

    public void back(View v){
        finish();
    }
}
