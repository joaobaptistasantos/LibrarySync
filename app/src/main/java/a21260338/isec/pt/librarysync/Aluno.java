package a21260338.isec.pt.librarysync;

public class Aluno extends Utilizador {

    private Integer nrAluno;

    public Aluno(String email, String password, Integer nrAluno) {
        super(email, password);
        this.nrAluno = nrAluno;
    }

    public Integer GetNrAluno() {
        return nrAluno;
    }
}