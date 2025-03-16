import java.util.Scanner;

class Substring {
    public static int maisLonga(String str) {
        int max = 0;
        int n = str.length();

        for (int i = 0; i < n; i++) {
            boolean[] encontrados = new boolean[256];
            int cont = 0;

            for (int j = i; j < n; j++) {
                char c = str.charAt(j);

                if (encontrados[c]) {
                    j = n;
                } else {
                    encontrados[c] = true;
                    cont++;
                }
            }

            if (cont > max) {
                max = cont;
            }
        }

        return max;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        while (!str.equals("FIM")) {
            System.out.println(maisLonga(str));
            str = sc.nextLine();
        }

        sc.close();
    }
}