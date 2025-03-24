#include <stdio.h>

int main()
{
    int array[10];
    int n = 0;
    ordenar(array, n);
}

void ordernar(int array[10], int n)
{
    for(int i = 0; i < n; i++)
    {
        int temp = array[i];
        int j = i -1;
        while((j >= 0) && (array[j] > temp))
        {
            array[j+1] = array[j];
            j--;
        }
        array[j+1]  = temp;
    }
}