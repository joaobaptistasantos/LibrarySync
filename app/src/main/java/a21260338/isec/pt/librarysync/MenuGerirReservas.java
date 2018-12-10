package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class MenuGerirReservas extends Activity {

    private Utilizador ativo;
    private Reservas reservas;
    private String[] getData = new String[3];
    private Date dataReserva = null;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gerir_reservas);

        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
        reservas = (Reservas) getIntent().getSerializableExtra("reservas");
        getData = getIntent().getStringArrayExtra("data");

        tv = (TextView) findViewById(R.id.TVgerirReservas);

        int day = Integer.parseInt(getData[0]);
        int month = Integer.parseInt(getData[1]) - 1;
        int year = Integer.parseInt(getData[2]);

        dataReserva = new Date(year, month, day);

        for (Reserva r: reservas.GetListData()) {
            if(tv.getText().equals("Sem reservas em seu nome")==true){
                tv.setMaxLines(0);
                tv.setText("");
            }
            if(r.getDataReserva().before(dataReserva) == false){
                tv.setMaxLines(tv.getMaxLines() + 7);
                if(r.getResponsavel().getEmail().equals(ativo.getEmail())){
                    tv.append("\nGabinete: " + r.getGabinete().getNrGabinete());
                    tv.append("\nResponsável: " + r.getResponsavel().getEmail());
                    tv.append("\nHora Início: " + r.getHoraInicio().toString());
                    tv.append("\nHora Fim: " + r.getHoraFim().toString());
                    tv.append("\nData Reserva: " + r.getDataReserva().getDate() + "/" + (r.getDataReserva().getMonth()+1) + "/" + r.getDataReserva().getYear());
                }
                tv.append("\n");
            }
        }
        tv.setMovementMethod(new ScrollingMovementMethod());
    }

    public void back(View v){
        finish();
    }
}
