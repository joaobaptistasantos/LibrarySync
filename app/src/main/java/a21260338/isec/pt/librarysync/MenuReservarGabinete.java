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
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MenuReservarGabinete extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;
    private DatePicker picker;
    private FragmentTransaction transaction;
    private Fragment newFragment;
    private int grupoGabinetesAtual = 1;
    private String turno = null;
    private View turnoAnterior = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reservar_gabinete);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");

        TextView dataTV = (TextView) findViewById(R.id.dataText_MenuReservarGabinete);
        dataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));

        newFragment = new Gabinete_1_2();
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, newFragment);
        transaction.commit();

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
        Log.d("Useres: ", "Gabinetes: " + grupoGabinetesAtual + "turno: " + turno);
        turnoAnterior = view;
    }
}
