package a21260338.isec.pt.librarysync;

public class Aluno extends Utilizador {

    int nrAluno;

    public Aluno(String Email, String Password) {
        super(Email, Password);
    }

    public int getNrAluno() {
        return nrAluno;
    }

    public void setNrAluno(int nrAluno) {
        this.nrAluno = nrAluno;
    }

    public void editaInformacao(String Email, String Password){

    }
}