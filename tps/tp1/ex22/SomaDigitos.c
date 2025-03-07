#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int somaDigitos(int n);

int main()
{
    char entrada[10];
    
    scanf("%s", entrada);
    while (strcmp(entrada, "FIM") != 0) // Compara a string corretamente
    {
        int n = atoi(entrada); // Converte string para inteiro
        printf("%d\n", somaDigitos(n));
        scanf("%s", entrada);
    }
    return 0;
}

int somaDigitos(int n)
{
    if (n == 0)
    {
        return 0;
    }
    else
    {
        return n % 10 + somaDigitos(n / 10);
    }
}
