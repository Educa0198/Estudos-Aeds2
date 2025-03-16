import java.util.Scanner;

class Senhas {
    public static boolean validacao(String pwd) {
        boolean res = false;
        int n = pwd.length();

        boolean upper = false, lower = false, digit = false, special = false;

        for (int i = 0; i < n; i++) {
            char c = pwd.charAt(i);

            if (Character.isUpperCase(c)) {
                upper = true;
            } else if (Character.isLowerCase(c)) {
                lower = true;
            } else if (Character.isDigit(c)) {
                digit = true;
            } else if (isSpecialCharacter(c)) {
                special = true;
            }

            if (upper && lower && digit && special && n >= 8) {
                res = true;
            }
        }

        return res;
    }

    public static boolean isSpecialCharacter(char c) {
        return !Character.isLetterOrDigit(c);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String pwd = sc.nextLine();

        while (!pwd.equals("FIM")) {
            if (!pwd.equals("FIM")) {
                if (validacao(pwd)) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            }

            pwd = sc.nextLine();
        }

        sc.close();
    }
}