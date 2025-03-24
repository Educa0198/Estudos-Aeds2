
import java.util.*;
public class Diamante {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String str;
        int esquerdo,direito;
        int tamanho;
        for(int i = 0 ; i < n; i++)
        {
            str = sc.nextLine();
            esquerdo= 0;
            direito = 0;
            tamanho = str.length();
            for(int j = 0; j < tamanho; j++)
            {
                if(str.charAt(j) == '<')
                {
                    esquerdo++;
                }
                if(str.charAt(j) == '>')
                {
                    direito++;
                }
            }
            if(esquerdo > direito)
            {
                System.out.println(direito);
            }
            else
            {
                System.out.println(esquerdo);
            }
        }
    }
}
