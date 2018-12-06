package a21260338.isec.pt.librarysync;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reservas implements Serializable {

    private List<Reserva> reservas;

    public Reservas() {
        reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public Reserva getReserva(int index) {
        if(reservas.isEmpty())
            return null;

        if(index < 0 || index >= reservas.size())
            return null;

        return reservas.get(index);
    }

    public void adicionarReserva(Reserva reserva){
        reservas.add(reserva);
    }
}
