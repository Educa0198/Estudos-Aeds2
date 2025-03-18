package tads.fila;
import java.util.*;

public class Fila {
    
    private int[] array = new int[10]; // Tamanho fixo para a fila
    private int n = 0; // Número de elementos na fila

    public void push(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("ERRO: Fila cheia");
        }
        array[n] = x;
        n++;
    }

    public int pop() throws Exception {
        if (n == 0) {
            throw new Exception("ERRO: Fila vazia");
        }
        int resp = array[0]; // Armazena o primeiro elemento
        for (int i = 0; i < n - 1; i++) { // Desloca os elementos para a esquerda
            array[i] = array[i + 1];
        }
        n--; // Só decrementa depois de mover os elementos
        return resp;
    }

    public void printQueue() { // Método extra para visualização
        System.out.print("Fila: ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            Fila fila = new Fila();
            fila.push(1);
            fila.push(2);
            fila.push(3);
            fila.printQueue(); // Deve exibir: 1 2 3
            System.out.println("Removido: " + fila.pop()); // Remove 1
            fila.printQueue(); // Deve exibir: 2 3
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
