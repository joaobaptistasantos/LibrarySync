package a21260338.isec.pt.librarysync;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva extends Modelo implements Serializable {

    private String estadoReserva;
    private Time horaInicio;
    private Time horaFim;
    private Date dataReserva;

    private Gabinete gabinete;
    private Utilizador responsavel;
    private List<Material> material;
    private List<Utilizador> utilizadoresAssociados;

    public Reserva(Gabinete gabinete, Utilizador responsavel, Time horaInicio, Time horaFim, Date dataReserva){
        estadoReserva = "Reservado";
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dataReserva = dataReserva;

        this.gabinete = gabinete;
        this.responsavel = responsavel;
        material = new ArrayList<>();
        utilizadoresAssociados = new ArrayList<>();
    }

    public String getEstadoReserva() { return estadoReserva; }

    public void setEstadoReserva(String estadoReserva) { this.estadoReserva = estadoReserva; }

    public Time getHoraInicio() { return horaInicio; }

    public Time getHoraFim() { return horaFim; }

    public void setHoraFim(Time horaFim) { this.horaFim = horaFim; }

    public Date getDataReserva() { return dataReserva; }

    public Utilizador getResponsavel() { return responsavel; }

    public Gabinete getGabinete() { return gabinete; }
}