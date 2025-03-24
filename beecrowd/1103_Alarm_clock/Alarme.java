import java.util.*;

public class Alarme {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h1, m1, h2, m2;

        while (true) {
            // Lendo os horários
            h1 = sc.nextInt();
            m1 = sc.nextInt();
            h2 = sc.nextInt();
            m2 = sc.nextInt();

            // Condição de parada
            if (h1 == 0 && m1 == 0 && h2 == 0 && m2 == 0) {
                break;
            }

            // Converter horários para minutos
            int inicio = h1 * 60 + m1;
            int fim = h2 * 60 + m2;

            // Se o alarme toca no dia seguinte
            if (fim <= inicio) {
                fim += 1440; // Adiciona 24 horas em minutos
            }

            // Calcula tempo dormido
            int dormiu = fim - inicio;
            System.out.println(dormiu);
        }

        sc.close();
    }
}
