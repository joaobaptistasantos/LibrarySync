package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MenuReserva extends Activity {

    private Utilizadores utilizadores;
    private Utilizador ativo;
    private Reservas reservas;
    private int contador;
    private ImageButton previous;
    private ImageButton next;
    private ConstraintLayout gabineteAndArrows;
    private TextView nrGabinete;
    private TextView dataGabinete;
    private TextView horarioGabinete;
    private TextView estadoGabinete;
    private Reserva atual;
    private TextView hiddenResponsavelTitle;
    private TextView hiddenResponsavelText;

    // É o mesmo menu reserva do utilizador mas tem dois campos com visibility a gone (responsavel)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_reserva);

        utilizadores = (Utilizadores) getIntent().getSerializableExtra("utilizadores");
        ativo = (Utilizador) getIntent().getSerializableExtra("ativo");
        reservas = (Reservas) getIntent().getSerializableExtra("reservas");

        contador = 0;

        gabineteAndArrows = (ConstraintLayout) findViewById(R.id.mostraGabinete);
        previous = (ImageButton) findViewById(R.id.anteriorReserva);
        next = (ImageButton) findViewById(R.id.proximaReserva);
        nrGabinete = (TextView) findViewById(R.id.gabineteNumero_MenuReserva);
        dataGabinete = (TextView) findViewById(R.id.dataText_MenuReservar);
        horarioGabinete = (TextView) findViewById(R.id.horarioText_MenuReservar);
        estadoGabinete = (TextView) findViewById(R.id.estadoText_MenuReservar);
        hiddenResponsavelTitle = (TextView) findViewById(R.id.responsavelTitle_MenuReservar);
        hiddenResponsavelText = (TextView) findViewById(R.id.responsavelText_MenuReservar);

        if(ativo.getClass() == Aluno.class){
            hiddenResponsavelTitle.setVisibility(View.GONE);
            hiddenResponsavelText.setVisibility(View.GONE);
        }
        else{
            hiddenResponsavelTitle.setVisibility(View.VISIBLE);
            hiddenResponsavelText.setVisibility(View.VISIBLE);
        }

        if(reservas.getReservas().isEmpty())
            gabineteAndArrows.setVisibility(View.GONE);
        else {
            atual = reservas.getReserva(contador);

            if(atual != null){
                gabineteAndArrows.setVisibility(View.VISIBLE);

                nrGabinete.setText("Gabinete nº" + atual.getGabinete().getNrGabinete());
                hiddenResponsavelText.setText(atual.getResponsavel().getEmail());
                dataGabinete.setText(atual.getDataReserva().toString());
                horarioGabinete.setText(atual.getHoraInicio().toString() + " : " + atual.getHoraFim().toString());
                estadoGabinete.setText(atual.getEstadoReserva());

                if(contador == 0)
                    previous.setEnabled(false);
                else
                    previous.setEnabled(true);

                if(contador == reservas.getReservas().size()-1)
                    next.setEnabled(false);
                else
                    next.setEnabled(true);
            }
            else
                gabineteAndArrows.setVisibility(View.GONE);
        }
    }

    public void back(View v){
        finish();
    }

    public void previous(View v){
        contador--;

        if(contador == 0)
            previous.setEnabled(false);
        else
            previous.setEnabled(true);

        atual = reservas.getReserva(contador);

        if(atual != null){
            nrGabinete.setText("Gabinete nº" + atual.getGabinete().getNrGabinete());
            hiddenResponsavelText.setText(atual.getResponsavel().getEmail());
            dataGabinete.setText(atual.getDataReserva().toString());
            horarioGabinete.setText(atual.getHoraInicio().toString() + " : " + atual.getHoraFim().toString());
            estadoGabinete.setText(atual.getEstadoReserva());
        }
        else
            gabineteAndArrows.setVisibility(View.GONE);

        if(ativo.getClass() == Aluno.class){
            hiddenResponsavelTitle.setVisibility(View.GONE);
            hiddenResponsavelText.setVisibility(View.GONE);
        }
        else{
            hiddenResponsavelTitle.setVisibility(View.VISIBLE);
            hiddenResponsavelText.setVisibility(View.VISIBLE);
        }
    }

    public void next(View v){
        contador++;

        if(contador == reservas.getReservas().size()-1)
            next.setEnabled(false);
        else
            next.setEnabled(true);

        atual = reservas.getReserva(contador);

        if(atual != null){
            nrGabinete.setText("Gabinete nº" + atual.getGabinete().getNrGabinete());
            hiddenResponsavelText.setText(atual.getResponsavel().getEmail());
            dataGabinete.setText(atual.getDataReserva().toString());
            horarioGabinete.setText(atual.getHoraInicio().toString() + " : " + atual.getHoraFim().toString());
            estadoGabinete.setText(atual.getEstadoReserva());
        }
        else
            gabineteAndArrows.setVisibility(View.GONE);

        if(ativo.getClass() == Aluno.class){
            hiddenResponsavelTitle.setVisibility(View.GONE);
            hiddenResponsavelText.setVisibility(View.GONE);
        }
        else{
            hiddenResponsavelTitle.setVisibility(View.VISIBLE);
            hiddenResponsavelText.setVisibility(View.VISIBLE);
        }
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
