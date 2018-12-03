package a21260338.isec.pt.librarysync;

public class Recepcionista extends Utilizador {

    private static int contadorRecepcionistas = 1;
    private int nrRecepcionista;

    public Recepcionista(String email, String password) {
        super(email, password);
        nrRecepcionista = contadorRecepcionistas++;
    }

    public int getNrRecepcionista() {
        return nrRecepcionista;
    }
}