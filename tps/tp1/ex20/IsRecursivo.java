import java.util.Scanner;

public class IsRecursivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {
            if (soVogal(palavra, 0)) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            if (soConsoante(palavra, 0))
            {
                System.out.print("SIM ");
            }
            else
            {
                System.out.print("NAO ");
            }
            if (EhInteiro(palavra))
            {
                System.out.print("SIM ");
            }
            else{
                System.out.print("NAO ");
            }
            if(EhReal(palavra))
            {
                System.out.print("SIM ");
            }
            else{
                System.out.print("NAO ");
            }
            System.out.printf("\n");
            palavra = sc.nextLine(); // Lê a próxima entrada
        }

        sc.close();
    }

    public static boolean soVogal(String palavra, int index) {
        if (index == palavra.length()) {
            return true; // Caso base: percorreu toda a string sem encontrar consoantes
        }

        char letra = Character.toLowerCase(palavra.charAt(index)); // Considera maiúsculas e minúsculas
        if ("aeiou".indexOf(letra) == -1) {
            return false; // Encontrou uma consoante ou caractere inválido
        }

        return soVogal(palavra, index + 1);
    }

    public static boolean soConsoante(String palavra, int index)
    {
        if (index == palavra.length())
        {
            return true;
        }
        char letra = Character.toLowerCase(palavra.charAt(index));
        letra = palavra.charAt(index);
        if(letra < 'a' || letra > 'z')
        {
            return false;
        }
        if ( letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')
        {
            return false;
        }

        return soConsoante(palavra, index + 1);
    }

    public static boolean EhInteiro(String palavra) {
        try {
            Integer.parseInt(palavra);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean EhReal(String palavra) {
        try {
            Double.parseDouble(palavra);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
