import java.util.*;

public class insercao {
    
    int [] array;
    int n;
    
    public insercao()
    {
        this(10);
    }
    
    public insercao(int tamanho)
    {
        array = new int[tamanho];
        n = 0;
    }
    

    public void ordena()
    {
        for(int i = 0; i  < n; i++)
        {
            int temp = array[i];
            int j = i-1;
            while ((j >= 0) && (array[j] > temp))
            {
                array[j+1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }
    
    
    public static void main(String[] args) {
        
    }
}
