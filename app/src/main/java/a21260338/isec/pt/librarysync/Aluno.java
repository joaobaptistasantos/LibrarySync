package a21260338.isec.pt.librarysync;

public class Aluno extends Utilizador {

    private static int contadorAlunos = 1;
    private int nrAluno;

    public Aluno(String email, String password) {
        super(email, password);
        nrAluno = contadorAlunos++;
    }

    public int getNrAluno() {
        return nrAluno;
    }
}