import java.util.Scanner;

public class SomaDigitos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // Lê a primeira entrada como string
        while (!input.equals("FIM")) {
            int numero = Integer.parseInt(input); //transforma a enrada de string em int
            System.out.println(SomaDigitos(numero));
            input = sc.nextLine(); // Lê o próximo número ou "FIM"
        }
    }

    public static int SomaDigitos(int numero) {
        if (numero == 0) {
            return 0; 
        } else {
            return numero % 10 + SomaDigitos(numero / 10); 
        }
    }
}
