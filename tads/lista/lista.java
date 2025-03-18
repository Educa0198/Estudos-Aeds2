package tads.lista;
import java.util.*;



public class lista {
    private int[] array;
    private int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        lista minhaLista = new lista(10);
        do {
            // Exibe o menu
            System.out.println("Escolha uma opção:");
            System.out.println("1. Inserir no início");
            System.out.println("2. Inserir no final");
            System.out.println("3. Inserir em uma posição");
            System.out.println("4. Remover do início");
            System.out.println("5. Remover do final");
            System.out.println("6. Remover de uma posição");
            System.out.println("7. Mostrar a lista");
            System.out.println("0. Sair");
            opcao = sc.nextInt();

            // Processa a opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("Digite o valor a inserir:");
                    int valor1 = sc.nextInt();
                    try {
                        minhaLista.inserirInicio(valor1);
                        System.out.println("Valor inserido com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro: Lista cheia.");
                    }
                    break;

                case 2:
                    System.out.println("Digite o valor a inserir:");
                    int valor2 = sc.nextInt();
                    try {
                        minhaLista.inserirFinal(valor2);
                        System.out.println("Valor inserido com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro: Lista cheia.");
                    }
                    break;

                case 3:
                    System.out.println("Digite o valor a inserir:");
                    int valor3 = sc.nextInt();
                    System.out.println("Digite a posição:");
                    int pos3 = sc.nextInt();
                    try {
                        minhaLista.inserir(valor3, pos3);
                        System.out.println("Valor inserido com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro: Lista cheia ou posição inválida.");
                    }
                    break;

                case 4:
                    try {
                        int removido4 = minhaLista.removerInicio();
                        System.out.println("Removido: " + removido4);
                    } catch (Exception e) {
                        System.out.println("Erro: Lista vazia.");
                    }
                    break;

                case 5:
                    try {
                        int removido5 = minhaLista.removerFim();
                        System.out.println("Removido: " + removido5);
                    } catch (Exception e) {
                        System.out.println("Erro: Lista vazia.");
                    }
                    break;

                case 6:
                    System.out.println("Digite a posição a remover:");
                    int pos6 = sc.nextInt();
                    try {
                        int removido6 = minhaLista.remover(pos6);
                        System.out.println("Removido: " + removido6);
                    } catch (Exception e) {
                        System.out.println("Erro: Lista vazia ou posição inválida.");
                    }
                    break;

                case 7:
                    minhaLista.mostrar();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
        sc.close();

    }

    public lista() {
        this(6);
    }

    public lista(int tamanho) {
        array = new int[tamanho];
        n = 0;
    }

    public void inserirInicio(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("ERRO");
        } else {
            for (int i = n; i > 0; i--) {
                array[i] = array[i - 1];
            }
            array[0] = x;
            n++;
        }
    }

    public void inserirFinal(int x) throws Exception {
        if (n >= array.length) {
            throw new Exception("ERRO");
        } else {
            array[n] = x;
            n++;
        }
    }

    public void inserir(int x, int posicao) throws Exception {
        if (n >= array.length) {
            throw new Exception("ERRO");
        } else {
            for (int i = n; i > posicao; i--) {
                array[i] = array[i - 1];
            }
            array[posicao] = x;
            n++;
        }
    }

    public int removerInicio() throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        } else {
            n--;
            int resp = array[0];
            for (int i = 0; i < n; i++) {
                array[i] = array[i + 1];
            }
            return resp;
        }
    }

    public int removerFim() throws Exception {
        if (n == 0) {
            throw new Exception("ERRO");
        }

        n--;
        int resp = array[n];
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
        System.out.print("[ ");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("]");
    }
}
