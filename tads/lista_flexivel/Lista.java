
import java.util.*;

public class Lista {
    private Celula primeiro, ultimo;

    public Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro.getProx(); i != null; i = i.getProx()) {
            tamanho++;
        }
        return tamanho;
    }

    public void inserirFim(int x) {
        ultimo.setProx(new Celula(x));
        ultimo = ultimo.getProx();
    }

    public int removerInicio() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro!");
        Celula tmp = primeiro;
        primeiro = primeiro.getProx();
        int elemento = primeiro.getElemento();
        tmp.setProx(null);
        tmp = null;
        return elemento;
    }

    public void inserirInicio(int x) {
        Celula tmp = new Celula(x);
        tmp.setProx(primeiro.getProx());
        primeiro.setProx(tmp);
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public int removerFim() throws Exception {
        if (primeiro == ultimo)
            throw new Exception("Erro!");
        Celula i;
        for (i = primeiro; i.getProx() != ultimo; i = i.getProx())
            ;
        int elemento = ultimo.getElemento();
        ultimo = i;
        ultimo.setProx(null);
        i = ultimo.getProx();
        return elemento;
    }

    public void inserir(int x, int pos) throws Exception {
        int tamanho = tamanho();
        if (pos < 0 || pos > tamanho) {
            throw new Exception("Erro!");
        } else if (pos == 0) {
            inserirInicio(x);
        } else if (pos == tamanho) {
            inserirFim(x);
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.getProx())
                ;
            Celula tmp = new Celula(x);
            tmp.setProx(i.getProx());
            i.setProx(tmp);
            i = null;
            tmp = i;
        }
    }

    public int remover(int pos) throws Exception {
        int elemento, tamanho = tamanho();
        if (primeiro == ultimo || pos < 0 || pos >= tamanho) {
            throw new Exception("Erro!");
        } else if (pos == 0) {
            elemento = removerInicio();
        } else if (pos == tamanho - 1) {
            elemento = removerFim();
        } else {
            Celula i = primeiro;
            for (int j = 0; j < pos; j++, i = i.getProx())
                ;
            Celula tmp = i.getProx();
            elemento = tmp.getElemento();
            i.setProx(tmp.getProx());
            tmp.setProx(null);
            tmp = null;
            i = tmp;
        }
        return elemento;
    }

    public void mostrar() {
        System.out.print("Lista: ");
        for (Celula i = primeiro.getProx(); i != null; i = i.getProx()) {
            System.out.print(i.getElemento() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            Lista lista = new Lista();

            System.out.println("Inserindo no início:");
            lista.inserirInicio(10);
            lista.inserirInicio(20);
            lista.inserirInicio(30);
            lista.mostrar(); // Lista: 30 20 10

            System.out.println("Inserindo no fim:");
            lista.inserirFim(40);
            lista.inserirFim(50);
            lista.mostrar(); // Lista: 30 20 10 40 50

            System.out.println("Inserindo na posição 2:");
            lista.inserir(25, 2);
            lista.mostrar(); // Lista: 30 20 25 10 40 50

            System.out.println("Removendo do início: " + lista.removerInicio()); // Remove 30
            lista.mostrar();

            System.out.println("Removendo do fim: " + lista.removerFim()); // Remove 50
            lista.mostrar();

            System.out.println("Removendo da posição 2: " + lista.remover(2)); // Remove 10
            lista.mostrar();

            System.out.println("Tamanho final da lista: " + lista.tamanho());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
