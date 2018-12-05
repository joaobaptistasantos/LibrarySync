package a21260338.isec.pt.librarysync;

public class Docente extends Utilizador {

    private Integer nrDocente;

    public Docente(String email, String password, Integer nrDocente) {
        super(email, password);
        this.nrDocente = nrDocente;
    }

    public Integer GetNrDocente() {
        return nrDocente;
    }

    public String ImprimeEstatisticas(){
        return "";
    }
}