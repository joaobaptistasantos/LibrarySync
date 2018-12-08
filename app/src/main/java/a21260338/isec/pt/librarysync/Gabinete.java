package a21260338.isec.pt.librarysync;

import java.io.Serializable;

public class Gabinete extends Modelo implements Serializable {

    private int nrGabinete;

    public Gabinete(int nrGabinete){
        this.nrGabinete = nrGabinete;
    }

    public int getNrGabinete(){
        return nrGabinete;
    }
}
