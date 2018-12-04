package a21260338.isec.pt.librarysync;

public class Extensao extends Material{

    private int nrEntradas;
    public Extensao(int nrMaterial, int nrEntradas)
    {
        super(nrMaterial);
        this.nrEntradas = nrEntradas;
    }
    public int GetNrEntradas(){return this.nrEntradas;}
}
