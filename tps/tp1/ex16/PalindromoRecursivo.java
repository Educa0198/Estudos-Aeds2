import java.util.Scanner;

public class PalindromoRecursivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String palavra = sc.nextLine();

        while (!palavra.equals("FIM")) {
            if (ehPalindromo(palavra, 0, palavra.length() - 1)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            palavra = sc.nextLine(); 
        }

        sc.close();
    }

    public static boolean ehPalindromo(String palavra, int esquerda, int direita) {
        if (esquerda >= direita) {
            return true;
        }
        if (palavra.charAt(esquerda) != palavra.charAt(direita)) {
            return false;
        }
        return ehPalindromo(palavra, esquerda + 1, direita - 1);
    }
}
