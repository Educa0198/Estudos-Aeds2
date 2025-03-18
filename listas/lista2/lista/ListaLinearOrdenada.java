package listas.lista2.lista;
import java.util.Scanner;

public class ListaLinearOrdenada {
    private int[] array;
    private int n;

    public ListaLinearOrdenada() {
        this(10);
    }

    public ListaLinearOrdenada(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    public void insercao(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("ERRO");
        }
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = x;
        n++;
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public int removerInicio() throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        }
        int resp = array[0];
        n--;
        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public int removerFim() throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        }
        int resp = array[n];
        n--;
        return resp;
    }

    public int remover(int posicao) throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        }
        int resp = array[posicao];
        n--;
        for (int i = posicao; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public void mostrar() {
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public boolean isOrdenada() {
        for (int i = 0; i < n; i++) {
            int swapped = 0; // Flag to check if any swaps occurred
            int temp;
            for (int j = 0; j < n - i - 1; j++) // The last i elements are already sorted
            {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = 1; // Swap occurred
                }
            }
            // If no elements were swapped, the array is already sorted
            if (swapped == 0)   
            {
                return true;
            }
        }
        return false;
    }

    public boolean pesquisar(int elem) {
        for (int i = 0; i < n; i++) {
            if (array[i] == elem) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaLinearOrdenada lista = new ListaLinearOrdenada();
        int opcao, valor, posicao;

        boolean menuAtivo = true;

        while (menuAtivo) {
            System.out.println("Menu:");
            System.out.println("1 - Inserir um elemento");
            System.out.println("2 - Remover do início");
            System.out.println("3 - Remover do fim");
            System.out.println("4 - Remover de uma posição");
            System.out.println("5 - Mostrar lista");
            System.out.println("6 - Verificar se a lista está ordenada");
            System.out.println("7 - Pesquisar um elemento");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor para inserir: ");
                        valor = scanner.nextInt();
                        lista.insercao(valor);
                        break;
                    case 2:
                        System.out.println("Elemento removido do início: " + lista.removerInicio());
                        break;
                    case 3:
                        System.out.println("Elemento removido do fim: " + lista.removerFim());
                        break;
                    case 4:
                        System.out.print("Digite a posição para remover: ");
                        posicao = scanner.nextInt();
                        System.out.println("Elemento removido: " + lista.remover(posicao));
                        break;
                    case 5:
                        System.out.print("Lista: ");
                        lista.mostrar();
                        break;
                    case 6:
                        if (lista.isOrdenada()) {
                            System.out.println("A lista está ordenada.");
                        } else {
                            System.out.println("A lista não está ordenada.");
                        }
                        break;
                    case 7:
                        System.out.print("Digite o valor para pesquisar: ");
                        valor = scanner.nextInt();
                        if (lista.pesquisar(valor)) {
                            System.out.println("Elemento encontrado.");
                        } else {
                            System.out.println("Elemento não encontrado.");
                        }
                        break;
                    case 8:
                        System.out.println("Saindo...");
                        menuAtivo = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
