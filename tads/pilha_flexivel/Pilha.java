import java.util.*;
import tads.common.Celula;

public class Pilha extends Celula {

    private Celula topo;
    public Pilha()
    {
        topo = null;
    }
    



    public void push(int x)  {
      
        Celula tmp = new Celula(x);
        tmp.setProx(topo);
        topo = tmp;
        tmp = null;
    }

    public int pop() throws Exception {
      if (topo == null){
        throw new Exception("erro");
      }
      int elemento = topo.getElemento();
      Celula tmp = topo;
      topo = topo.getProx();
      tmp.setProx(null);
      tmp = null;
      return elemento;
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
