package a21260338.isec.pt.librarysync;

public class Recepcionista extends Utilizador {

    int nrRecepcionista;

    public Recepcionista(String Email, String Password) {
        super(Email, Password);
    }

    public int getNrRecepcionista() {
        return nrRecepcionista;
    }

    public void setNrRecepcionista(int nrRecepcionista) {
        this.nrRecepcionista = nrRecepcionista;
    }

    public void editaInformacao(String Email, String Password){

    }
}