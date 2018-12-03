package a21260338.isec.pt.librarysync;

import java.io.Serializable;

public class Utilizador implements Serializable {

    private String email;
    private String password;

    public Utilizador(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean emailCorreto(String email){
        return this.email.equals(email);
    }

    public boolean passwordCorreta(String password){
        return this.password.equals(password);
    }

    public boolean autentica(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }
}