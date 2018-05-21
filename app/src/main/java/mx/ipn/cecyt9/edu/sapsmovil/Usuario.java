package mx.ipn.cecyt9.edu.sapsmovil;

/* * Created by Alexis on 20/05/2018.*/
import java.io.Serializable;

public class Usuario implements  Serializable{
    private Integer id;
    private String rem;
    private String men;
    private String res;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
