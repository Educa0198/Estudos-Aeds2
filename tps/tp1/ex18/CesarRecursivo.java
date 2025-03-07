import java.util.Scanner;

public class CesarRecursivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        processarEntrada(sc);
        sc.close();
    }

    public static void processarEntrada(Scanner sc) {
        String palavra = sc.nextLine();
        if (palavra.equals("FIM")) {
            return;
        }
        System.out.println(cifrar(palavra, 0, new StringBuilder()));
        processarEntrada(sc);
    }

    public static StringBuilder cifrar(String palavra, int index, StringBuilder palavraNova) {
        if (index == palavra.length()) {
            return palavraNova;
        }
        char letra = (char) (palavra.charAt(index) + 3);
        if((palavra.charAt(index) + 3) > 125 ) //confere se a letra tem valor maior que # na tabela ASCII, se sim, altera o caracter para o caracter de valor invalido usado no verde
                {
                    letra = (char) 65533;
                }
        palavraNova.append(letra);
        return cifrar(palavra, index + 1, palavraNova);
    }
}
