import java.util.Scanner;
import java.io.*;

public class Arquivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        float numero;
        
        try {
            
            File arquivo = new File("arquivo.txt");
            DataOutputStream escrever = new DataOutputStream(new FileOutputStream(arquivo));
            
           
            for (int i = 0; i < n; i++) {
                numero = sc.nextFloat();
                escrever.writeFloat(numero);
            }
            escrever.close();
            
         
            RandomAccessFile lerArquivo = new RandomAccessFile(arquivo, "r"); 
            long tamanho = lerArquivo.length();
            
            
            while (tamanho > 0) {
                tamanho -= 4; 
                lerArquivo.seek(tamanho);
                numero = lerArquivo.readFloat();
                if(numero % 1 == 0 )
                {
                    System.out.println((int)numero);
                }
                else{
                    System.out.println(numero);
                }
            }
            
            lerArquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao manipular o arquivo.");
            e.printStackTrace();
        }
        
        sc.close();
    }
}
