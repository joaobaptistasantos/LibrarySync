package a21260338.isec.pt.librarysync;

import android.content.Intent;
import android.net.Uri;

import java.util.ArrayList;

public class Utilizadores extends Controlador<Utilizador> {

    public Utilizadores(ArrayList<Utilizador> utilizadores) {
        super(utilizadores);
        AdcicionaUtilizadoresDeTeste();
    }


    public Aluno AddUtilizador(String email, String password, String password2) throws InvalidEmailException, InvalidDifferentPasswordsException, InvalidPasswordException, AccountAlreadyExistsException {
        Integer nrAluno = 5;    //TODO: METER COMO PARAMETRO (Adicionar campo às views primeiro)
        ValidaEmailRegisto(email);
        ValidaPasswords(password, password2);

        Aluno novo = new Aluno(email, password, nrAluno);
        InsereData(novo);
        return novo;
    }

    private boolean EmailExiste(String email) {
        for(Utilizador u : GetListData())
            if(u.emailCorreto(email))
                return true;
        return false;
    }

    private void EmailContainsAtSign(String email) throws InvalidEmailException {
        if(!email.contains("@"))
            throw new InvalidEmailException("Email Inválido");
    }

    private void ValidaEmailRegisto(String email) throws InvalidEmailException, AccountAlreadyExistsException {
        EmailContainsAtSign(email);

        if (EmailExiste(email))
            throw new AccountAlreadyExistsException("Email Já registado");
    }

    private void ValidaPassword(String password) throws InvalidPasswordException{
        if(password.isEmpty())
            throw new InvalidPasswordException("Password por preencher!");

        if(!password.matches("[a-zA-Z0-9]*"))
            throw new InvalidPasswordException("Password deve conter apenas letras e números!");
    }

    private void ValidaPasswords(String password, String password2) throws InvalidPasswordException, InvalidDifferentPasswordsException {
        ValidaPassword(password);

        if(!password.equals(password2))
            throw new InvalidDifferentPasswordsException("Passwords têm que ser iguais!");
    }

    public Utilizador Autentica(String email, String password) throws InvalidAuthenticationException{
        for(Utilizador u : GetListData())
            if(u.autentica(email, password))
                return u;
        throw new InvalidAuthenticationException("Autenticação falhada!");
    }

    public Intent RecuperarConta(String email) throws InvalidAccountRecover {
        String subject = "Recupera password LibrarySync";

        for (Utilizador u: GetListData()) {
            if(u.emailCorreto(email)) {
                String password = u.getPassword();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, password);
                intent.setType("message/rfc822");

                return intent;
            }
        }

        throw new InvalidAccountRecover("Conta impossível de recuperar!");
    }

    public boolean MudarPassord(Utilizador user, String passwordAntiga, String passwordNova, String passwordNova2) throws InvalidDifferentPasswordsException, InvalidPasswordException {
        if(!(user.passwordCorreta(passwordAntiga)))
            throw new InvalidDifferentPasswordsException("Password atual errada!");

        ValidaPasswords(passwordNova, passwordNova2);

        String email = user.getEmail();

        for(Utilizador u : GetListData())
            if(u.emailCorreto(email)) {
                u.setPassword(passwordNova);
                return true;
            }
        return false;
    }

    private void AdcicionaUtilizadoresDeTeste(){
        InsereData(new Docente("docente@isec.pt", "docente", 12121212));
        InsereData(new Recepcionista("recepcionista@isec.pt", "recepcionista", 21212121));
    }
}