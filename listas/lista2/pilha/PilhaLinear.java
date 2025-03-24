import java.util.*;

public class PilhaLinear {
    private int[] array;
    private int n;

    public PilhaLinear() {
        this(10);
    }

    public PilhaLinear(int max) {
        array = new int[max];
        n = 0;
    }

    public void empilhar(int x) {
        array[n] = x;
        n++;
    }

    public int desempilhar() {
        n--;
        return array[n];
    }

    public void mostrar() {
        for (int i = n - 1; i >= 0; i--) {
            System.out.println(array[i] + " ");
        }
    }

    public boolean pesquisar(int x) {
        for (int i = 0; i < n; i++) {
            if (array[i] == x) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PilhaLinear pilha = new PilhaLinear();

        int opcao;
        do {
            System.out.println("Menu:");
            System.out.println("1. Empilhar");
            System.out.println("2. Desempilhar");
            System.out.println("3. Mostrar a pilha");
            System.out.println("4. Pesquisar um elemento");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para empilhar: ");
                    int valor = scanner.nextInt();
                    pilha.empilhar(valor);
                    break;

                case 2:
                    if (pilha.n > 0) {
                        System.out.println("Elemento desempilhado: " + pilha.desempilhar());
                    } else {
                        System.out.println("A pilha está vazia.");
                    }
                    break;

                case 3:
                    System.out.println("Elementos na pilha:");
                    pilha.mostrar();
                    break;

                case 4:
                    System.out.print("Digite o valor para pesquisar: ");
                    int buscar = scanner.nextInt();
                    if (pilha.pesquisar(buscar)) {
                        System.out.println("Elemento encontrado na pilha.");
                    } else {
                        System.out.println("Elemento não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}
