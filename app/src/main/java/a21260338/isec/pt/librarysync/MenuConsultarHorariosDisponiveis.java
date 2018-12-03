package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MenuConsultarHorariosDisponiveis extends Activity {

    private DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_consultar_horarios_disponiveis);

        TextView dataTV = (TextView) findViewById(R.id.dataText_MenuConsultarHorariosDisponiveis);
        dataTV.setText(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
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
                TextView dataTV = (TextView) findViewById(R.id.dataText_MenuConsultarHorariosDisponiveis);
                dataTV.setText(picker.getDayOfMonth() + "/" + (picker.getMonth()+1) + "/" + picker.getYear());

                // Desfaz o dialog
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
