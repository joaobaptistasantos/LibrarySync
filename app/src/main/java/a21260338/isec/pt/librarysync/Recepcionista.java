package a21260338.isec.pt.librarysync;

public class Recepcionista extends Utilizador {

    private Integer nrRecepcionista;

    public Recepcionista(String email, String password, Integer nrRecepcionista) {
        super(email, password);
        this.nrRecepcionista = nrRecepcionista;
    }

    public Integer GetNrRecepcionista() {
        return nrRecepcionista;
    }
}