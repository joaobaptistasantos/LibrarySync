package a21260338.isec.pt.librarysync;

import java.util.List;
import java.util.ArrayList;

public abstract class Controlador<T extends Modelo> {
    private List<T> listData = new ArrayList<T>();
    protected Controlador(ArrayList<T> listData){
        this.listData = listData;
    }
    protected List<T> GetListData(){
        return listData;
    }

    protected void InsereData(T data){
        listData.add(data);
    }

    protected boolean RemoveData(T data){
        return listData.remove(data);
    }

    protected boolean DataExiste(T data){
        return listData.contains(data);
    }
}
