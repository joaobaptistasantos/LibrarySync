package a21260338.isec.pt.librarysync;

public class Utilizador {
    String password;
    String email;


    public Utilizador(String Email, String Password){

        this.email = Email;
        this.password = Password;
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

    public boolean passwordValido(String password){
        return this.password.equals(password);
    }

    public boolean autentica(String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }
}