import java.util.*;

public class bubblesort {

    private int[] array;
    private int n;

    public bubblesort()
    {
        this(10);
    }


    public bubblesort(int tamanho)
    {
        array = new int[tamanho];
        n = 0;
    }


    public void ordena(int n)
    {
        int temp;
        for(int i = 0; i < n; i++)
        {
            for(int j = 0 ; j < n - i -1; j++)
            {
                if(array[j] > array[j+1])
                {
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        
    }
}
