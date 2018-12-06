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
}
