#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

void getstring(char palavra[], int maxSize);
bool ehPalindromo(char palavra[]);

int main()
{
    char palavra[50];
    getstring(palavra, sizeof(palavra));

    if(ehPalindromo(palavra))
    {
        printf("SIM\n");
    }
    else
    {
        printf("NAO\n");
    }

    return 0;
}

// Gera uma string a partir da entrada do teclado.
void getstring(char palavra[], int maxSize)
{
    fgets(palavra, maxSize, stdin);

    //Remove espaço em branco da string para nao ocorrer erros na comparação
    size_t tamanho = strlen(palavra);
    if (tamanho > 0 && palavra[tamanho - 1] == '\n') {
        palavra[tamanho - 1] = '\0';
    }
}


//Confere se eh palindromo ou nao e retorna um bool dependendo do caso.
bool ehPalindromo(char palavra[])
{
    int tamanho = strlen(palavra);

    for(int i = 0; i < tamanho / 2; i++) 
    {
        if(palavra[i] != palavra[tamanho - i - 1]) 
        {
            return false; 
        }
    }
    return true; 
}
