package a21260338.isec.pt.librarysync;

public class Caneta extends Material{

    private String cor;
    public Caneta(int nrMaterial, String cor){ super(nrMaterial); this.cor = cor;}
    public String GetCor(){ return this.cor; }
}
