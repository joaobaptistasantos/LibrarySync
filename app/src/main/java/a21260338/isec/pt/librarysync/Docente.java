package a21260338.isec.pt.librarysync;

public class Docente extends Utilizador {

    int nrDocente;

    public Docente(String Email, String Password) {
        super(Email, Password);
    }

    public int getNrDocente() {
        return nrDocente;
    }

    public void setNrDocente(int nrDocente) {
        this.nrDocente = nrDocente;
    }

    public String imprimeEstatisticas(){
        return "";
    }

    public void editaInformacao(String email, String password){

    }
}