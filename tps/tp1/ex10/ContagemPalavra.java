import java.util.Scanner;

public class ContagemPalavra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String frase = sc.nextLine();
        int contador;
        while (!frase.equals("FIM"))
        {
            contador = 1; 
            for(int i = 0; i < frase.length(); i++)
            {
                if(frase.charAt(i) == ' ')
                {
                    contador++;
                }
            }
            System.out.println(contador);
            frase = sc.nextLine();
        }
    }
}
