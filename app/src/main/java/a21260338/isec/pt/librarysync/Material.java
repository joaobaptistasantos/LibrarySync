package a21260338.isec.pt.librarysync;

import java.io.Serializable;

public class Material implements Serializable {

    private int nrMaterial;
    //private String estado;

    //public Material() {}
    public Material(int nrMaterial){this.nrMaterial = nrMaterial;}
    public int Get_N_Material(){ return this.nrMaterial; }
}
