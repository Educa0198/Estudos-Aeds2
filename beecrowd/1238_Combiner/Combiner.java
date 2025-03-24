import java.util.*;

public class Combiner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int tamanho1, tamanho2;
        String str1;
        
        for(int m = 0 ; m < n; m++)
        {
            int p = 0;
            str1 = sc.nextLine();
            while(!(str1.charAt(p) == ' '))
            {
                p++;
            }
            tamanho1 = p;
            StringBuilder str2 = new StringBuilder();
            for(int l = p + 1; l < str1.length(); l++ )
            {
                str2.append(str1.charAt(l));
            }
            tamanho2 = str2.length();
            StringBuilder palavranova = new StringBuilder();
            if(tamanho1 < tamanho2)
            {
                for(int i =0; i < tamanho1; i++)
                {
                    palavranova.append(str1.charAt(i));
                    palavranova.append(str2.charAt(i));
                }
                for(int j  = tamanho1;  j < tamanho2; j++)
                {
                    palavranova.append(str2.charAt(j));
                }
            }
            if(tamanho2 < tamanho1)
            {
                for(int i = 0; i < tamanho2; i++)
                {
                    palavranova.append(str1.charAt(i));
                    palavranova.append(str2.charAt(i));
                }
                for(int j = tamanho2; j < tamanho1; j++)
                {
                    palavranova.append(str1.charAt(j));
                }
            }
            System.out.println(palavranova);
        }

    }
}
