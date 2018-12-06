package a21260338.isec.pt.librarysync;

import android.util.Log;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public abstract class Controlador<T extends Modelo> implements Serializable {
    private List<T> listData = new ArrayList<T>();
    protected Controlador(ArrayList<T> listData){
        try {
            this.listData = listData;
        } catch (java.lang.NullPointerException e) {
            Log.d("NullPointerException", e.toString());
        }

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
