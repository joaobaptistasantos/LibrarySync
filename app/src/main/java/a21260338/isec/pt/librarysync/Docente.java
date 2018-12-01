package a21260338.isec.pt.librarysync;

public class Docente extends Utilizador {

    private static int contadorDocentes = 1;
    private int nrDocente;

    public Docente(String email, String password) {
        super(email, password);
        nrDocente = contadorDocentes++;
    }

    public int getNrDocente() {
        return nrDocente;
    }

    public String imprimeEstatisticas(){
        return "";
    }
}