package tads.lista_encadeda_dupla;

import java.util.*;

public class CelulaDupla {
    private int elemento;
    private CelulaDupla prox,ant;

    public CelulaDupla()
    {
        this(0);
    }



    public CelulaDupla(int elemento) {
        this.elemento = elemento;
        this.prox = this.ant = null;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public CelulaDupla getProx() {
        return prox;
    }

    public void setProx(CelulaDupla prox) {
        this.prox = prox;
    }

    public CelulaDupla getAnt()
    {
        return ant;
    }

    public void setAnt(CelulaDupla ant)
    {
        this.ant = ant;
    }
}
