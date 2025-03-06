#include <stdio.h>

int main()
{
    int n;
    scanf("%d", &n);
    float numero;
    float saida[n];
    char linha[256];
    FILE *arquivo = fopen("arquivo.txt", "w");
    for(int i = 0; i < n; i++)
    {
        scanf("%f", &numero);
        if(i == n -1)
        {
            fprintf(arquivo, "%.3f", numero);
        }
        else 
        {
            fprintf(arquivo, "%.3f\n", numero);
        }
    }
    fclose(arquivo);
    arquivo = fopen("arquivo.txt", "r");
    for(int j =0; j < n; j++)
    {   
        fgets(linha, sizeof(linha), arquivo);
        sscanf(linha,"%f", &saida[j]);       
    }
    for( int m = n -1 ; m >= 0; m--)
    {
        if ((int)saida[m] == saida[m]) {
            printf("%.0f\n", saida[m]);
        } else {
            printf("%g\n", saida[m]);
        }
    }

}
