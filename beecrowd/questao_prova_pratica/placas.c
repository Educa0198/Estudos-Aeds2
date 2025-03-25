#include <stdio.h>

int confere(char placa[256], int p);

int main()
{
    char placa[256];
    fgets(placa, sizeof(placa), stdin);

    int p = 0;
    while (placa[p] != '\n' && placa[p] != '\0')
    {
        p++;
    }

    placa[p] = '\0';  // Removendo o '\n' do final
    printf("%d\n", confere(placa, p));
}

int confere(char placa[256], int p)
{
    int checado = 0;

    if (p < 7)
    {
        return checado;  // Placa muito curta
    }

    if (placa[3] == '-')
    {
        // Formato antigo: "AAA-1234"
        if ((placa[0] >= 'A' && placa[0] <= 'Z') &&
            (placa[1] >= 'A' && placa[1] <= 'Z') &&
            (placa[2] >= 'A' && placa[2] <= 'Z'))
        {
            checado = 1;
            for (int i = 4; i < p; i++)
            {
                if (placa[i] < '0' || placa[i] > '9')
                {
                    return 0;  // Número inválido
                }
            }
        }
        return checado;
    }

    // Possível formato novo: "AAA1A23"
    for (int i = 0; i < 3; i++)
    {
        if (!(placa[i] >= 'A' && placa[i] <= 'Z'))
        {
            return 0;  // Primeiros 3 caracteres devem ser letras
        }
    }

    if (placa[3] < '0' || placa[3] > '9')
    {
        return 0;  // O quarto caractere deve ser um número
    }

    if (!(placa[4] >= 'A' && placa[4] <= 'Z'))
    {
        return 0;  // O quinto caractere deve ser uma letra
    }

    if (placa[5] < '0' || placa[5] > '9' || placa[6] < '0' || placa[6] > '9')
    {
        return 0;  // Os últimos dois caracteres devem ser números
    }

    return 2;  // Formato novo válido
}
