import java.util.*;

public class Pilha {

    private int[] array = new int[10];
    private int n = 0;

    public void push(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("ERRO: Pilha cheia");
        }
        array[n] = x;
        n++;
    }

    public int pop() throws Exception {
        if (n == 0) {
            throw new Exception("ERRO: Pilha vazia");
        }
        n--;  // Decrementa antes de acessar o elemento
        return array[n]; 
    }

    public static void main(String[] args) {
        try {
            Pilha pilha = new Pilha();
            pilha.push(10);
            pilha.push(20);
            System.out.println(pilha.pop()); // Deve imprimir 20
            System.out.println(pilha.pop()); // Deve imprimir 10
            System.out.println(pilha.pop()); // Deve lançar exceção
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
