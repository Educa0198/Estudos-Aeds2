import java.util.*;



public class Watson {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int  i = 0; i < n ; i++)
        {
            int t = sc.nextInt();
            int[] array =  new int[t];
            for(int j = 0; j < t; j++)
            {
                array[j] = sc.nextInt();
            }
            System.out.println(ordena(array));
        }
    }


    public static int ordena(int[] array)
    {
        int trocas = 0;
        for(int i = 0 ; i < array.length; i++)
        {
            int temp = array[i];
            int j = i -1;
            while((j >= 0) && (temp < array[j]))
            {
                array[j+1] = array[j];
                j--;
                trocas++;
            }
            array[j+1] = temp;
        }
        return trocas;
    }
}
 