#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool ehPalindromo(char palavra[256]);

int main()
{
    char palavra[256];
    fgets(palavra, sizeof(palavra), stdin);
    while( strcmp(palavra, "FIM\n") != 0)
    {
        if(ehPalindromo(palavra))
        {
            printf("SIM\n");
        }
        else{
            printf("NAO\n");
        }
        fgets(palavra, sizeof(palavra), stdin);
    }
}

bool ehPalindromo(char palavra[256])
{
    int tamanho = 0;
    while(palavra[tamanho] != '\n')
    {
        tamanho++;
    }
    for(int i = 0 ; i < tamanho; i++)
    {
        if(palavra[i] != palavra[tamanho -i -1])
        {
            return false;
        }
    }
    return true;
}