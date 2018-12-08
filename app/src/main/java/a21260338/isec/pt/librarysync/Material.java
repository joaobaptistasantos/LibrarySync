package a21260338.isec.pt.librarysync;

import java.io.Serializable;

public class Material extends Modelo implements Serializable {

    private int nrMaterial;
    private String estado;

    public Material() {}
    public Material(int nrMaterial){}
    public int Get_Material(){ return this.nrMaterial; }
}
