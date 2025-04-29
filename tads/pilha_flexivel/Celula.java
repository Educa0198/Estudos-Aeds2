
import java.util.*;

public class Celula {
    private int elemento;
    private Celula prox;

    public Celula()
    {
        this(0);
    }


    public Celula(int elemento) {
        this.elemento = elemento;
        this.prox = null;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public Celula getProx() {
        return prox;
    }

    public void setProx(Celula prox) {
        this.prox = prox;
    }
}
