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

    public boolean equals(Aluno o){
        if(this.nrAluno == o.nrAluno &&
           this.getPassword() == o.getPassword() &&
           this.getEmail() == this.getEmail())
            return true;
        return false;
    }
}