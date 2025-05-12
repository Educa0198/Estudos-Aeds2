import java.util.*;
import java.io.*;
import java.text.*;

public class Jogadores {

    String Nome;
    String Foto;
    Date Nascimento;
    int id;
    int[] Times;

    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        System.out.println(id + " " + Nome + " " + sdf.format(Nascimento) + " " + Foto + " " + Arrays.toString(Times));
    }

    public void ler(String linha) throws ParseException {
        int virgulas  = 0;
        for(int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == ',') {
                virgulas++;
            }
        }
        
        String[] partes = linha.split(",");
        this.id = Integer.parseInt(partes[6]);
        this.Nome = partes[1];
        this.Foto = partes[2];

        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        this.Nascimento = sdf.parse(partes[3]);

        // Inicializando o array de Times baseado na quantidade de times
        this.Times = new int[virgulas - 5]; // Ajustando o tamanho com base na quantidade de times
        int j = 0;
        for(int i = 7; i < partes.length; i++) {   
            String Sonumero = partes[i];
            if (!Sonumero.isEmpty()) {
                int numero = Integer.parseInt(Sonumero);
                this.Times[j] = numero;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String linha = sc.nextLine();
        while (!linha.equals("FIM")) {
            try {
                Jogadores jogador = new Jogadores();
                jogador.ler(linha);
                jogador.imprimir();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            linha = sc.nextLine();
        }
        sc.close();
    }
}
