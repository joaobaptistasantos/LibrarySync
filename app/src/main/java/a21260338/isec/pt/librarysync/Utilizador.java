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

    public boolean passwordValido(String Password){
        if(Password == password){
            return true;
        }else{
            return false;
        }
    }

    public void editaInformacao(String Email, String Password){
        email = Email;
        password = Password;
    }

    public boolean autentica(String Email, String Password){
        if(email == Email && password == Password){
            return true;
        }else{
            return false;
        }
    }
}