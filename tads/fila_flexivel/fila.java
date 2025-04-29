package tads.fila_flexivel;

import tads.common.Celula;

public class fila {
    private Celula primeiro, ultimo;

    public fila()
    {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public void inserir(int x)
    {
        ultimo.setProx(new Celula(x));
        ultimo = ultimo.getProx();
    }

    public int remover() throws Exception {
        if(primeiro == ultimo)
        throw new Exception("Erro!");
        Celula tmp = primeiro;
        primeiro = primeiro.getProx();
        int elemento = primeiro.getElemento();
        tmp.setProx(null);
        tmp = null;
        return elemento;
    }



    public static void main(String[] args) {
        try {
            fila fila = new fila();
            fila.inserir(1);
            fila.inserir(2);
            fila.inserir(3);
            System.out.println("Removido: " + fila.remover());
            System.out.println("Removido: " + fila.remover());
            System.out.println("Removido: " + fila.remover()); // Remove 1
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
