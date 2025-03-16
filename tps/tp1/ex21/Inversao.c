#include <stdio.h>
#include <string.h>

void inverteString(char palavra[256], char palavrainvertida[256], int tamanho, int index);

int main()
{
    char palavra[256], palavrainvertida[256];
    fgets(palavra, sizeof(palavra), stdin);
    
    // Remover o caractere '\n' se presente
    int tamanho = 0;
    while(palavra[tamanho] != '\n' && palavra[tamanho] != '\0') {
        tamanho++;
    }
    palavra[tamanho] = '\0'; // Certificar-se de que a string termina corretamente
    
    // Loop para processar as strings até "FIM"
    while(strcmp(palavra, "FIM") != 0)
    {
        inverteString(palavra, palavrainvertida, tamanho, 0);
        printf("%s\n", palavrainvertida);
        
        // Lê a próxima linha
        fgets(palavra, sizeof(palavra), stdin);
        
        // Remover o caractere '\n' da nova linha lida
        tamanho = 0;
        while(palavra[tamanho] != '\n' && palavra[tamanho] != '\0') {
            tamanho++;
        }
        palavra[tamanho] = '\0'; // Certificar-se de que a string termina corretamente
    }

    return 0;
}

void inverteString(char palavra[256], char palavrainvertida[256], int tamanho, int index)
{
    if(index == tamanho)
    {
        palavrainvertida[index] = '\0'; // Adiciona o terminador de string
        return;
    }
    else
    {
        palavrainvertida[index] = palavra[tamanho - index - 1];
        inverteString(palavra, palavrainvertida, tamanho, index + 1);
    }
}
