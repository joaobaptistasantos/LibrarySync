package a21260338.isec.pt.librarysync;

public class Extensao extends Material{

    private int nrEntradas;
    public Extensao(int nrMaterial)
    {
        super(nrMaterial);
        this.nrEntradas = 3;
    }
    public int GetNrEntradas(){return this.nrEntradas;}
}
