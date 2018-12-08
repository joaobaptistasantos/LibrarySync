package a21260338.isec.pt.librarysync;

import java.io.Serializable;
import java.util.ArrayList;

public class Reservas extends Controlador<Reserva> implements Serializable {
    public Reservas(ArrayList<Reserva> reservas) {
        super(reservas);
    }
}
