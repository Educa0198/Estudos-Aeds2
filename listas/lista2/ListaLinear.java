import java.util.*;

public class ListaLinear {
    
    private int[] array;
    private int n;
    
    
    public ListaLinear(int max)
    {
        array = new int[max];
    }

    public void inserirInicio(int x) throws Exception
    {
        if( n >= array.length)
        {
            throw new Exception("ERRO");
        }
        for(int i = n; i > 0; i--)
        {
            array[i] = array[i-1];
        }
        array[0] = x;
        n++;
    }

    public void inserirFim(int x) throws Exception
    {
        if(n >= array.length)
        {
            throw new Exception("ERRO");
        }
        array[n] = x;
        n++;
    }


    public void inserir(int x, int posicao) throws Exception
    {
        if (n >= array.length)
        {
            throw new Exception("ERRO");
        }
        for(int i = n; i > posicao; i--)
        {
            array[i] = array [i -1];
        }
        array[posicao] = x;
        n++;
    }

    public int removerInicio() throws Exception
    {
        if(n == 0)
        {
            throw new Exception("ERRO");

        }
        int resp = array[0];
        n--;
        for(int i = 0; i < n ; i++)
        {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public int removerFim() throws Exception
    {
        if ( n == 0)
        {
            throw new Exception("ERRO");
        }
        int resp = array[n];
        n--;
        return resp;
    }

    public int remover(int posicao) throws Exception
    {
        if(n == 0)
        {
            throw new Exception("ERRO");
        }
        int resp = array[posicao];
        n--;
        for(int i = posicao; i < n; i++)
        {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public void mostrar()
    {
        for(int i = 0; i < n; i++)
        {
            System.out.print(array[i] + " ");
        }
    }

    public boolean pesquisar(int elem)
    {
        boolean match = false;
        for(int i = 0 ; i < n; i++)
        {
            if(array[i] == elem)
            {
                return true;
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o tamanho máximo da lista: ");
        int tamanho = scanner.nextInt();
        ListaLinear lista = new ListaLinear(tamanho);
        int opcao;

        do {
            System.out.println("\nMENU:");
            System.out.println("1 - Inserir no início");
            System.out.println("2 - Inserir no fim");
            System.out.println("3 - Inserir em posição específica");
            System.out.println("4 - Remover do início");
            System.out.println("5 - Remover do fim");
            System.out.println("6 - Remover de posição específica");
            System.out.println("7 - Mostrar lista");
            System.out.println("8 - Pesquisar elemento");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Digite o valor a ser inserido no início: ");
                        lista.inserirInicio(scanner.nextInt());
                        break;
                    case 2:
                        System.out.print("Digite o valor a ser inserido no fim: ");
                        lista.inserirFim(scanner.nextInt());
                        break;
                    case 3:
                        System.out.print("Digite o valor a ser inserido: ");
                        int valor = scanner.nextInt();
                        System.out.print("Digite a posição: ");
                        int posicao = scanner.nextInt();
                        lista.inserir(valor, posicao);
                        break;
                    case 4:
                        System.out.println("Removido do início: " + lista.removerInicio());
                        break;
                    case 5:
                        System.out.println("Removido do fim: " + lista.removerFim());
                        break;
                    case 6:
                        System.out.print("Digite a posição do elemento a ser removido: ");
                        System.out.println("Removido: " + lista.remover(scanner.nextInt()));
                        break;
                    case 7:
                        System.out.print("Lista: ");
                        lista.mostrar();
                        break;
                    case 8:
                        System.out.print("Digite o elemento a ser pesquisado: ");
                        System.out.println(lista.pesquisar(scanner.nextInt()) ? "Elemento encontrado" : "Elemento não encontrado");
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (opcao != 0);

        scanner.close();
    }

}
