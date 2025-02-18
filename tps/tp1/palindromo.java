  import java.util.Scanner;
  

     public class palindromo{
        Scanner sc = new Scanner(System.in);

        public static void main(String[] args)
        {
            while (getPalavra() != "FIM") {
                
            
            if (ehPalindromo(getPalavra()))
            {
                System.out.printf("SIM\n");
            }
            else
            System.out.printf("NAO\n");
        }
        }

        String getPalavra()
        {
            String palavra = sc.nextLine();
            return palavra;
        }


        boolean ehPalindromo(String palavra)
        {
            
            int tamanho = palavra.length() - 1;
            
            for(int i =0; i < tamanho/2 ; i++)
            {
                if (palavra.charAt(0 ) != palavra.charAt(tamanho - i))
                {
                    return false; // Não é um palíndromo
                }
            }
        
            return true;
        }



     }