import java.util.Scanner;

public class Medalhas {

    private String nome;
    private int ouro;
    private int prata;
    private int bronze;

    // Construtor
    public Medalhas(String nome, int ouro, int prata, int bronze) {
        this.nome = nome;
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
    }

    public String getNome() {
        return nome;
    }

    public int getOuro() {
        return ouro;
    }

    public int getPrata() {
        return prata;
    }

    public int getBronze() {
        return bronze;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.print("Digite o número de países: ");
        n = sc.nextInt();
        while (n < 0 || n > 500) {
            System.out.println("Out of Bounds");
            n = sc.nextInt();
        }

        Medalhas[] paises = new Medalhas[n];
        sc.nextLine(); // Consumir a quebra de linha após o nextInt()

        for (int i = 0; i < n; i++) {
            String nome = sc.nextLine();
            int ouro = sc.nextInt();
            int prata = sc.nextInt();
            int bronze = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha após os números
            paises[i] = new Medalhas(nome, ouro, prata, bronze);
        }

        ordena(paises);
        printa(paises, n);

        sc.close();
    }

    public static void ordena(Medalhas[] paises) {
        // Ordenação pelo critério correto: ouro -> prata -> bronze -> nome
        for (int i = 1; i < paises.length; i++) {
            Medalhas temppais = paises[i];
            int j = i - 1;

            // Ordenação principal (ouro)
            while (j >= 0 && temppais.getOuro() > paises[j].getOuro()) {
                paises[j + 1] = paises[j];
                j--;
            }
            paises[j + 1] = temppais;
        }

        // Ordenação secundária (prata, mantendo a ordem do ouro)
        for (int i = 1; i < paises.length; i++) {
            Medalhas temppais = paises[i];
            int j = i - 1;

            while (j >= 0 && temppais.getOuro() == paises[j].getOuro() && temppais.getPrata() > paises[j].getPrata()) {
                paises[j + 1] = paises[j];
                j--;
            }
            paises[j + 1] = temppais;
        }

        // Ordenação terciária (bronze, mantendo a ordem do ouro e prata)
        for (int i = 1; i < paises.length; i++) {
            Medalhas temppais = paises[i];
            int j = i - 1;

            while (j >= 0 && temppais.getOuro() == paises[j].getOuro() &&
                   temppais.getPrata() == paises[j].getPrata() &&
                   temppais.getBronze() > paises[j].getBronze()) {
                paises[j + 1] = paises[j];
                j--;
            }
            paises[j + 1] = temppais;
        }

        // Ordenação alfabética (apenas quando ouro, prata e bronze são iguais)
        for (int i = 1; i < paises.length; i++) {
            Medalhas temppais = paises[i];
            int j = i - 1;

            while (j >= 0 && temppais.getOuro() == paises[j].getOuro() &&
                   temppais.getPrata() == paises[j].getPrata() &&
                   temppais.getBronze() == paises[j].getBronze() &&
                   temppais.getNome().compareTo(paises[j].getNome()) < 0) {
                paises[j + 1] = paises[j];
                j--;
            }
            paises[j + 1] = temppais;
        }
    }

    public static void printa(Medalhas[] paises, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(paises[i].getNome() + " ");
            System.out.print(paises[i].getOuro() + " ");
            System.out.print(paises[i].getPrata() + " ");
            System.out.println(paises[i].getBronze());
        }
    }
}
