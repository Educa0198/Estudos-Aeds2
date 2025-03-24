package beecrowd.espelho;
import java.util.*;



public class espelho {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int limiteInferior, limiteSuperior;
        limiteInferior = sc.nextInt();
        limiteSuperior = sc.nextInt();
        printaNormal(limiteInferior, limiteSuperior);
        printaInvertido(limiteInferior, limiteSuperior);

    }



    public static void printaNormal(int limiteInferior, int limiteSuperior)
    {
        for(int i = limiteInferior; i <= limiteSuperior; i++)
        {
            System.out.print(i);
        }
    }

    public static void printaInvertido(int limiteInferior, int limiteSuperior)
    {
        for(int i = limiteSuperior; i >= limiteInferior; i--)
        {
            System.out.print(i);
        }
    }
}