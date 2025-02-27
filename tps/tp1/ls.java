import java.util.Scanner;

public class ls {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();

        while(!palavra.equals("FIM"))
        {
            SoVogal(palavra);
            SoConsoante(palavra);
            EhInteiro(palavra);
            EhReal(palavra);
            System.out.printf("\n");
            palavra = sc.nextLine();
        }
    }


    static void SoVogal(String palavra)
    {
        int tamanho = palavra.length();
        boolean SoVogal = true;
        char letra;
        for(int i = 0 ; i < tamanho ; i++)
        {
            letra = palavra.charAt(i);
            if ( letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u')
            {
                SoVogal = false;
            }
        }
        if(SoVogal)
        {
            System.out.print("SIM ");
        }
        else
        {
            System.out.print("NAO ");
        }
    }

    static void SoConsoante(String palavra)
    {
        int tamanho = palavra.length();
        boolean SoConsoante = true;
        char letra;
        for(int i = 0 ; i < tamanho ; i++)
        {
            letra = palavra.charAt(i);
            if(letra < 'a' || letra > 'z')
            {
                SoConsoante = false;
            }
            if ( letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')
            {
                SoConsoante = false;
            }
        }
        if(SoConsoante)
        {
            System.out.print("SIM ");
        }
        else
        {
            System.out.print("NAO ");
        }
    }

    static void EhInteiro(String palavra) {
        try {
            Integer.parseInt(palavra); // Tenta converter a string para número inteiro
            System.out.print("SIM ");
        } catch (NumberFormatException e) {
            System.out.print("NAO ");
        }
    }
    
    static void EhReal(String palavra) {
        try {
            Double.parseDouble(palavra); // Tenta converter a string para um número real
            System.out.print("SIM ");
        } catch (NumberFormatException e) {
            System.out.print("NAO ");
        }
    }
    
}
