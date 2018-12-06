package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MenuReservarGabinete extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;
    private Reservas reservas;
    private DatePicker picker;
    private FragmentTransaction transaction;
    private Fragment newFragment;
    private int grupoGabinetesAtual = 1;
    private String turno = null;
    private View turnoAnterior = null;
    private Time horaInicio = null;
    private Time horaFim = null;
    private int gabinete = 0;

    private Gabinete g1 = null;
    private Gabinete g2 = null;
    private Gabinete g3 = null;
    private Gabinete g4 = null;
    private Gabinete g5 = null;
    private Gabinete g6 = null;

    Calendar date = null;
    Date dataReserva = null;

    private Time primeiroTurno = new Time((9) * 60 * 60 *1000);
    private Time segundoTurno = new Time((12) * 60 * 60 *1000);
    private Time terceiroTurno = new Time((15) * 60 * 60 *1000);
    private Time quartoTurno = new Time((18) * 60 * 60 *1000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reservar_gabinete);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
        reservas = (Reservas) getIntent().getSerializableExtra("reservas");

        TextView dataTV = (TextView) findViewById(R.id.dataText_MenuReservarGabinete);
        dataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));


        Calendar date = Calendar.getInstance();
        /*
        date.set((picker.getYear() + 1900), (picker.getMonth() - 1), picker.getDayOfMonth());
        dataReserva = date.getTime();
        */
        newFragment = new Gabinete_1_2();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.commit();

        g1 = new Gabinete(1);
        g2 = new Gabinete(2);
        g3 = new Gabinete(3);
        g4 = new Gabinete(4);
        g5 = new Gabinete(5);
        g6 = new Gabinete(6);

        Button view;

        /*
        for (Reserva r: reservas.getReservas()) {
            if(r.getGabinete().getNrGabinete() == 1){
                if(r.getHoraInicio().toString().equals(primeiroTurno.toString()) == true){
                    view = findViewById(R.id.primeiroTurno_MenuReservarGabinete);
                    view.getBackground().setColorFilter(Color.parseColor("#FFFF00"), PorterDuff.Mode.DARKEN);Log.i("Useres", "ya4");
                }else
                if(r.getHoraInicio().toString().equals(segundoTurno.toString()) == true){
                    view = findViewById(R.id.segundoTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(terceiroTurno.toString()) == true){
                    view = findViewById(R.id.terceiroTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(quartoTurno.toString()) == true){
                    view = findViewById(R.id.quartoTurno_MenuReservarGabinete);
                }
            }else
            if(r.getGabinete().getNrGabinete() == 2){
                if(r.getHoraInicio().toString().equals(primeiroTurno.toString()) == true){
                    view = findViewById(R.id.primeiroTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(segundoTurno.toString()) == true){
                    view = findViewById(R.id.segundoTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(terceiroTurno.toString()) == true){
                    view = findViewById(R.id.terceiroTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(quartoTurno.toString()) == true){
                    view = findViewById(R.id.quartoTurno2_MenuReservarGabinete);
                }
            }else
            if(r.getGabinete().getNrGabinete() == 3){
                if(r.getHoraInicio().toString().equals(primeiroTurno.toString()) == true){
                    view = findViewById(R.id.primeiroTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(segundoTurno.toString()) == true){
                    view = findViewById(R.id.segundoTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(terceiroTurno.toString()) == true){
                    view = findViewById(R.id.terceiroTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(quartoTurno.toString()) == true){
                    view = findViewById(R.id.quartoTurno_MenuReservarGabinete);
                }
            }else
            if(r.getGabinete().getNrGabinete() == 4){
                if(r.getHoraInicio().toString().equals(primeiroTurno.toString()) == true){
                    view = findViewById(R.id.primeiroTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(segundoTurno.toString()) == true){
                    view = findViewById(R.id.segundoTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(terceiroTurno.toString()) == true){
                    view = findViewById(R.id.terceiroTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(quartoTurno.toString()) == true){
                    view = findViewById(R.id.quartoTurno2_MenuReservarGabinete);
                }
            }else
            if(r.getGabinete().getNrGabinete() == 5){
                if(r.getHoraInicio().toString().equals(primeiroTurno.toString()) == true){
                    view = findViewById(R.id.primeiroTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(segundoTurno.toString()) == true){
                    view = findViewById(R.id.segundoTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(terceiroTurno.toString()) == true){
                    view = findViewById(R.id.terceiroTurno_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(quartoTurno.toString()) == true){
                    view = findViewById(R.id.quartoTurno_MenuReservarGabinete);
                }
            }else
            if(r.getGabinete().getNrGabinete() == 6){
                if(r.getHoraInicio().toString().equals(primeiroTurno.toString()) == true){
                    view = findViewById(R.id.primeiroTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(segundoTurno.toString()) == true){
                    view = findViewById(R.id.segundoTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(terceiroTurno.toString()) == true){
                    view = findViewById(R.id.terceiroTurno2_MenuReservarGabinete);
                }else
                if(r.getHoraInicio().toString().equals(quartoTurno.toString()) == true){
                    view = findViewById(R.id.quartoTurno2_MenuReservarGabinete);
                }
            }
        }
        */
    }

    public void back(View v){
        finish();
    }

    public void alterarData(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        picker = new DatePicker(this);

        picker.setCalendarViewShown(false);

        builder.setTitle("Escolha a data da reserva:")
                .setView(picker);
        builder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView dataTV = (TextView) findViewById(R.id.dataText_MenuReservarGabinete);
                dataTV.setText(picker.getDayOfMonth() + "/" + (picker.getMonth()+1) + "/" + picker.getYear());

                // Desfaz o dialog
                dialog.dismiss();
            }
        });
        builder.show();
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

    public void concluido(View v){
/*
        date.set((picker.getYear()), (picker.getMonth()), picker.getDayOfMonth());
        dataReserva = date.getTime();
        */
        if(grupoGabinetesAtual == 1){
            if(turno.equals("primeiroTurno_MenuReservarGabinete") == true){
                gabinete = 1;
                horaInicio = new Time((9) * 60 * 60 *1000);
                horaFim = new Time((12) * 60 * 60 * 1000);
            }else
            if(turno.equals("segundoTurno_MenuReservarGabinete") == true ){
                gabinete = 1;
                horaInicio = new Time((12) * 60 * 60 *1000);
                horaFim = new Time((15) * 60 * 60 * 1000);
            }else
            if(turno.equals("terceiroTurno_MenuReservarGabinete") == true){
                gabinete = 1;
                horaInicio = new Time((15) * 60 * 60 *1000);
                horaFim = new Time((18) * 60 * 60 * 1000);
            }else
            if(turno.equals("quartoTurno_MenuReservarGabinete") == true){
                gabinete = 1;
                horaInicio = new Time((18) * 60 * 60 *1000);
                horaFim = new Time((21) * 60 * 60 * 1000);
            }else
            if(turno.equals("primeiroTurno2_MenuReservarGabinete") == true){
                gabinete = 2;
                horaInicio = new Time((9) * 60 * 60 *1000);
                horaFim = new Time((12) * 60 * 60 * 1000);
            }else
            if(turno.equals("segundoTurno2_MenuReservarGabinete") == true){
                gabinete = 2;
                horaInicio = new Time((12) * 60 * 60 *1000);
                horaFim = new Time((15) * 60 * 60 * 1000);
            }else
            if(turno.equals("terceiroTurno2_MenuReservarGabinete")==true){
                gabinete = 2;
                horaInicio = new Time((15) * 60 * 60 *1000);
                horaFim = new Time((18) * 60 * 60 * 1000);
            }else
            if(turno.equals("quartoTurno2_MenuReservarGabinete")==true){
                gabinete = 2;
                horaInicio = new Time((18) * 60 * 60 *1000);
                horaFim = new Time((21) * 60 * 60 * 1000);
            }
        }else
        if(grupoGabinetesAtual == 2){
            if(turno.equals("primeiroTurno_MenuReservarGabinete") == true){
                gabinete = 3;
                horaInicio = new Time((9) * 60 * 60 *1000);
                horaFim = new Time((12) * 60 * 60 * 1000);
            }else
            if(turno.equals("segundoTurno_MenuReservarGabinete") == true ){
                gabinete = 3;
                horaInicio = new Time((12) * 60 * 60 *1000);
                horaFim = new Time((15) * 60 * 60 * 1000);
            }else
            if(turno.equals("terceiroTurno_MenuReservarGabinete") == true){
                gabinete = 3;
                horaInicio = new Time((15) * 60 * 60 *1000);
                horaFim = new Time((18) * 60 * 60 * 1000);
            }else
            if(turno.equals("quartoTurno_MenuReservarGabinete") == true){
                gabinete = 3;
                horaInicio = new Time((18) * 60 * 60 *1000);
                horaFim = new Time((21) * 60 * 60 * 1000);
            }else
            if(turno.equals("primeiroTurno2_MenuReservarGabinete") == true){
                gabinete = 4;
                horaInicio = new Time((9) * 60 * 60 *1000);
                horaFim = new Time((12) * 60 * 60 * 1000);
            }else
            if(turno.equals("segundoTurno2_MenuReservarGabinete") == true){
                gabinete = 4;
                horaInicio = new Time((12) * 60 * 60 *1000);
                horaFim = new Time((15-1) * 60 * 60 * 1000);
            }else
            if(turno.equals("terceiroTurno2_MenuReservarGabinete")==true){
                gabinete = 4;
                horaInicio = new Time((15) * 60 * 60 *1000);
                horaFim = new Time((18) * 60 * 60 * 1000);
            }else
            if(turno.equals("quartoTurno2_MenuReservarGabinete")==true){
                gabinete = 4;
                horaInicio = new Time((18) * 60 * 60 *1000);
                horaFim = new Time((21) * 60 * 60 * 1000);
            }
        }else
        if(grupoGabinetesAtual == 3){
            if(turno.equals("primeiroTurno_MenuReservarGabinete") == true){
                gabinete = 5;
                horaInicio = new Time((9) * 60 * 60 *1000);
                horaFim = new Time((12) * 60 * 60 * 1000);
            }else
            if(turno.equals("segundoTurno_MenuReservarGabinete") == true ){
                gabinete = 5;
                horaInicio = new Time((12) * 60 * 60 *1000);
                horaFim = new Time((15) * 60 * 60 * 1000);
            }else
            if(turno.equals("terceiroTurno_MenuReservarGabinete") == true){
                gabinete = 5;
                horaInicio = new Time((15) * 60 * 60 *1000);
                horaFim = new Time((18-1) * 60 * 60 * 1000);
            }else
            if(turno.equals("quartoTurno_MenuReservarGabinete") == true){
                gabinete = 5;
                horaInicio = new Time((18) * 60 * 60 *1000);
                horaFim = new Time((21) * 60 * 60 * 1000);
            }else
            if(turno.equals("primeiroTurno2_MenuReservarGabinete") == true){
                gabinete = 6;
                horaInicio = new Time((9) * 60 * 60 *1000);
                horaFim = new Time((12) * 60 * 60 * 1000);
            }else
            if(turno.equals("segundoTurno2_MenuReservarGabinete") == true){
                gabinete = 6;
                horaInicio = new Time((12) * 60 * 60 *1000);
                horaFim = new Time((15) * 60 * 60 * 1000);
            }else
            if(turno.equals("terceiroTurno2_MenuReservarGabinete")==true){
                gabinete = 6;
                horaInicio = new Time((15) * 60 * 60 *1000);
                horaFim = new Time((18) * 60 * 60 * 1000);
            }else
            if(turno.equals("quartoTurno2_MenuReservarGabinete")==true){
                gabinete = 6;
                horaInicio = new Time((18) * 60 * 60 *1000);
                horaFim = new Time((21) * 60 * 60 * 1000);
            }
        }
        switch (gabinete){
            case 1:
                reservas.getReservas().add(new Reserva(g1, ativo, horaInicio, horaFim, dataReserva));
                break;
            case 2:
                reservas.getReservas().add(new Reserva(g2, ativo, horaInicio, horaFim, dataReserva));
                break;
            case 3:
                reservas.getReservas().add(new Reserva(g3, ativo, horaInicio, horaFim, dataReserva));
                break;
            case 4:
                reservas.getReservas().add(new Reserva(g4, ativo, horaInicio, horaFim, dataReserva));
                break;
            case 5:
                reservas.getReservas().add(new Reserva(g5, ativo, horaInicio, horaFim, dataReserva));
                break;
            case 6:
                reservas.getReservas().add(new Reserva(g6, ativo, horaInicio, horaFim, dataReserva));
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("reservas", reservas);
        setResult(1, intent);
        finish();
    }

    public void gabinetesSeguintes(View view) {
        if(grupoGabinetesAtual == 1){
        grupoGabinetesAtual++;
        newFragment = new Gabinete_3_4();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.commit();
        }else
        if(grupoGabinetesAtual == 2){
            grupoGabinetesAtual++;
            newFragment = new Gabinete_5_6();
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, newFragment);
            transaction.commit();
        }else return;
    }

    public void gabinetesAnteriores(View view) {
        if(grupoGabinetesAtual == 3){
            grupoGabinetesAtual--;
            newFragment = new Gabinete_3_4();
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, newFragment);
            transaction.commit();
        }else
        if(grupoGabinetesAtual == 2){
            grupoGabinetesAtual--;
            newFragment = new Gabinete_1_2();
            transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, newFragment);
            transaction.commit();
        }else return;
    }

    public void horaReserva(View view) {

        if(turnoAnterior!=null){
            turnoAnterior.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.DARKEN);
        }
        turno = getResources().getResourceEntryName(view.getId());
        view.getBackground().setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.DARKEN);
        turnoAnterior = view;
    }
}
