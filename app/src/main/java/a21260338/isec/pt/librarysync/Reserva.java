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
    //private List<Material> material;
    Material canetas;
    Material extensao;
    Material apagador;
    private List<Utilizador> utilizadoresAssociados;

    public Reserva(Gabinete gabinete, Utilizador responsavel, Time horaInicio, Time horaFim, Date dataReserva){
        estadoReserva = "Reservado";
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.dataReserva = dataReserva;

        this.gabinete = gabinete;
        this.responsavel = responsavel;
        //material = new ArrayList<>();
        canetas = new Caneta(Globals.materiais[0]);
        extensao = new Extensao(Globals.materiais[1]);
        apagador = new Apagador(Globals.materiais[2]);
        Globals.materiais[0]=0;
        Globals.materiais[1]=0;
        Globals.materiais[2]=0;
        //material.add(new Caneta(Globals.materiais[0]));
        //material.add(new Extensao(Globals.materiais[1]));
        //material.add(new Apagador(Globals.materiais[2]));
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

    public int getNumMateriais(){
        int num_mat = 0;
        //for (int i = 0; i < material.size(); i++) {
        //    num_mat += material.get(i).Get_N_Material();
        //}
        num_mat += canetas.Get_Material();
        num_mat += extensao.Get_Material();
        num_mat += apagador.Get_Material();
        return num_mat;
    }

    public int getNumMaterialByIndex(int index)
    {
        int ret_st=0;
        //return material.get(index).Get_N_Material();
        switch (index)
        {
            case 0:
                ret_st = canetas.Get_Material();
                break;
            case 1:
                ret_st = extensao.Get_Material();
                break;
            case 2:
                ret_st = apagador.Get_Material();
                break;
            default:
                break;
        }
        return ret_st;
    }
}