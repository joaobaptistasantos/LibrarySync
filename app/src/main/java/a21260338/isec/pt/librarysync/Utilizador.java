package a21260338.isec.pt.librarysync;

import java.io.Serializable;

public abstract class Utilizador extends Modelo implements Serializable{

    private String email;
    private String password;

    protected Utilizador(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public boolean emailCorreto(String email){
        return this.email.equals(email);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean passwordCorreta(String password){
        return this.password.equals(password);
    }

    public boolean autentica(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }
}