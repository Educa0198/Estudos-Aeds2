#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_STRING 500
#define MAX_ARRAY 1000

typedef struct {
    char showId[MAX_STRING];
    char type[MAX_STRING];
    char title[MAX_STRING];
    char director[MAX_STRING];
    char** cast;
    int castSize;
    char country[MAX_STRING];
    char dateAdded[MAX_STRING];
    int releaseYear;
    char rating[MAX_STRING];
    char duration[MAX_STRING];
    char** listedIn;
    int listedInSize;
} Show;

Show* lista[MAX_ARRAY];
int listaSize = 0;
int comparacoes = 0;
const char* matricula = "844412";
clock_t tempoInicial;

// Funções auxiliares
char* trim(char* str) {
    while (isspace((unsigned char)*str)) str++;
    if (*str == 0) return str;

    char* end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;
    end[1] = '\0';
    return str;
}

char** splitString(const char* input, const char* delimiter, int* count) {
    char* str = strdup(input);
    char* token;
    char** result = NULL;
    int i = 0;

    token = strtok(str, delimiter);
    while (token != NULL) {
        result = realloc(result, sizeof(char*) * (i + 1));
        result[i] = strdup(trim(token));
        i++;
        token = strtok(NULL, delimiter);
    }

    *count = i;
    free(str);
    return result;
}

void freeStringArray(char** array, int size) {
    for (int i = 0; i < size; i++) {
        free(array[i]);
    }
    free(array);
}

// Funções do Show
Show* createShow() {
    Show* show = (Show*)malloc(sizeof(Show));
    strcpy(show->showId, "NaN");
    strcpy(show->type, "NaN");
    strcpy(show->title, "NaN");
    strcpy(show->director, "NaN");
    
    show->cast = (char**)malloc(sizeof(char*));
    show->cast[0] = strdup("NaN");
    show->castSize = 1;
    
    strcpy(show->country, "NaN");
    strcpy(show->dateAdded, "NaN");
    show->releaseYear = 0;
    strcpy(show->rating, "NaN");
    strcpy(show->duration, "NaN");
    
    show->listedIn = (char**)malloc(sizeof(char*));
    show->listedIn[0] = strdup("NaN");
    show->listedInSize = 1;
    
    return show;
}

void setShowId(Show* show, const char* showId) {
    if (showId == NULL || strlen(showId) == 0) {
        strcpy(show->showId, "NaN");
    } else {
        strcpy(show->showId, showId);
    }
}

void setType(Show* show, const char* type) {
    if (type == NULL || strlen(type) == 0) {
        strcpy(show->type, "NaN");
    } else {
        strcpy(show->type, type);
    }
}

void setTitle(Show* show, const char* title) {
    if (title == NULL || strlen(title) == 0) {
        strcpy(show->title, "NaN");
    } else {
        // Remove aspas se existirem
        char temp[MAX_STRING];
        strcpy(temp, title);
        if (temp[0] == '"') {
            memmove(temp, temp + 1, strlen(temp));
            temp[strlen(temp) - 1] = '\0';
        }
        strcpy(show->title, temp);
    }
}

void setDirector(Show* show, const char* director) {
    if (director == NULL || strlen(director) == 0) {
        strcpy(show->director, "NaN");
    } else {
        strcpy(show->director, director);
    }
}

void setCast(Show* show, const char* castStr) {
    if (castStr == NULL || strlen(castStr) == 0 || 
        (strlen(castStr) == 1 && isspace(castStr[0]))) {
        freeStringArray(show->cast, show->castSize);
        show->cast = (char**)malloc(sizeof(char*));
        show->cast[0] = strdup("NaN");
        show->castSize = 1;
    } else {
        int count;
        char** castArray = splitString(castStr, ",", &count);
        
        // Ordenar (bubble sort simples)
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (strcmp(castArray[i], castArray[j]) > 0) {
                    char* temp = castArray[i];
                    castArray[i] = castArray[j];
                    castArray[j] = temp;
                }
            }
        }
        
        freeStringArray(show->cast, show->castSize);
        show->cast = castArray;
        show->castSize = count;
    }
}

void setCountry(Show* show, const char* country) {
    if (country == NULL || strlen(country) == 0) {
        strcpy(show->country, "NaN");
    } else {
        strcpy(show->country, country);
    }
}

void setDateAdded(Show* show, const char* dateAdded) {
    if (dateAdded == NULL || strlen(dateAdded) == 0) {
        strcpy(show->dateAdded, "NaN");
    } else {
        strcpy(show->dateAdded, dateAdded);
    }
}

void setReleaseYear(Show* show, int releaseYear) {
    if (releaseYear <= 0) {
        show->releaseYear = -1;
    } else {
        show->releaseYear = releaseYear;
    }
}

void setRating(Show* show, const char* rating) {
    if (rating == NULL || strlen(rating) == 0) {
        strcpy(show->rating, "NaN");
    } else {
        strcpy(show->rating, rating);
    }
}

void setDuration(Show* show, const char* duration) {
    if (duration == NULL || strlen(duration) == 0) {
        strcpy(show->duration, "NaN");
    } else {
        strcpy(show->duration, duration);
    }
}

void setListedIn(Show* show, const char* listedInStr) {
    if (listedInStr == NULL || strlen(listedInStr) == 0 ||
        (strlen(listedInStr) == 1 && isspace(listedInStr[0]))) {
        freeStringArray(show->listedIn, show->listedInSize);
        show->listedIn = (char**)malloc(sizeof(char*));
        show->listedIn[0] = strdup("NaN");
        show->listedInSize = 1;
    } else {
        int count;
        char** listedInArray = splitString(listedInStr, ",", &count);
        freeStringArray(show->listedIn, show->listedInSize);
        show->listedIn = listedInArray;
        show->listedInSize = count;
    }
}

void imprimirShow(Show* show) {
    printf("=> %s ## %s ## %s ## %s ## [", show->showId, show->title, show->type, show->director);
    
    for (int i = 0; i < show->castSize; i++) {
        printf("%s%s", show->cast[i], (i < show->castSize - 1) ? ", " : "");
    }
    
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", show->country, 
           (strcmp(show->dateAdded, "NaN") == 0 ? "NaN" : show->dateAdded), 
           show->releaseYear, show->rating, show->duration);
    
    for (int i = 0; i < show->listedInSize; i++) {
        printf("%s%s", show->listedIn[i], (i < show->listedInSize - 1) ? ", " : "");
    }
    
    printf("] ##\n");
}

void freeShow(Show* show) {
    if (show != NULL) {
        freeStringArray(show->cast, show->castSize);
        freeStringArray(show->listedIn, show->listedInSize);
        free(show);
    }
}

void lerShow(Show* show, const char* linha) {
    char* partes[MAX_STRING];
    int partCount = 0;
    char* linhaCopia = strdup(linha);
    char* token = strtok(linhaCopia, ",");
    
    while (token != NULL && partCount < MAX_STRING) {
        partes[partCount++] = token;
        token = strtok(NULL, ",");
    }
    
    // Processar cada campo
    if (partCount > 0) setShowId(show, partes[0]);
    if (partCount > 1) setType(show, partes[1]);
    if (partCount > 2) {
        char* titulo = partes[2];
        // Remover aspas se existirem
        if (titulo[0] == '"') {
            titulo++;
            titulo[strlen(titulo)-1] = '\0';
        }
        setTitle(show, titulo);
    }
    
    int i = 3;
    // Diretor
    if (i < partCount) {
        if (partes[i][0] == '"') {
            char director[MAX_STRING] = {0};
            strcpy(director, partes[i++]);
            
            while (i < partCount && partes[i][strlen(partes[i])-1] != '"') {
                strcat(director, ",");
                strcat(director, partes[i++]);
            }
            if (i < partCount) {
                strcat(director, ",");
                strcat(director, partes[i++]);
            }
            // Remover aspas
            memmove(director, director+1, strlen(director));
            director[strlen(director)-1] = '\0';
            setDirector(show, director);
        } else {
            setDirector(show, partes[i++]);
        }
    }
    
    // Cast
    if (i < partCount) {
        if (partes[i][0] == '"') {
            char cast[MAX_STRING] = {0};
            strcpy(cast, partes[i++]);
            
            while (i < partCount && partes[i][strlen(partes[i])-1] != '"') {
                strcat(cast, ",");
                strcat(cast, partes[i++]);
            }
            if (i < partCount) {
                strcat(cast, ",");
                strcat(cast, partes[i++]);
            }
            // Remover aspas
            memmove(cast, cast+1, strlen(cast));
            cast[strlen(cast)-1] = '\0';
            setCast(show, cast);
        } else {
            setCast(show, partes[i++]);
        }
    }
    
    // Country
    if (i < partCount) {
        if (partes[i][0] == '"') {
            char country[MAX_STRING] = {0};
            strcpy(country, partes[i++]);
            
            while (i < partCount && partes[i][strlen(partes[i])-1] != '"') {
                strcat(country, ",");
                strcat(country, partes[i++]);
            }
            if (i < partCount) {
                strcat(country, ",");
                strcat(country, partes[i++]);
            }
            // Remover aspas
            memmove(country, country+1, strlen(country));
            country[strlen(country)-1] = '\0';
            setCountry(show, country);
        } else {
            setCountry(show, partes[i++]);
        }
    }
    
    // Date Added
    if (i+1 < partCount) {
        char date[MAX_STRING] = {0};
        strcpy(date, partes[i++]);
        strcat(date, ", ");
        strcat(date, partes[i++]);
        // Remover aspas se existirem
        if (date[0] == '"') {
            memmove(date, date+1, strlen(date));
            date[strlen(date)-1] = '\0';
        }
        setDateAdded(show, date);
    }
    
    // Release Year
    if (i < partCount) {
        int ano;
        if (sscanf(partes[i], "%d", &ano) == 1) {
            setReleaseYear(show, ano);
        } else {
            setReleaseYear(show, -1);
        }
        i++;
    }
    
    // Rating
    if (i < partCount) {
        setRating(show, partes[i++]);
    }
    
    // Duration
    if (i < partCount) {
        setDuration(show, partes[i++]);
    }
    
    // Listed In
    if (i < partCount) {
        if (partes[i][0] == '"') {
            char listed[MAX_STRING] = {0};
            strcpy(listed, partes[i++]);
            
            while (i < partCount && partes[i][strlen(partes[i])-1] != '"') {
                strcat(listed, ",");
                strcat(listed, partes[i++]);
            }
            if (i < partCount) {
                strcat(listed, ",");
                strcat(listed, partes[i++]);
            }
            // Remover aspas
            memmove(listed, listed+1, strlen(listed));
            listed[strlen(listed)-1] = '\0';
            setListedIn(show, listed);
        } else {
            setListedIn(show, partes[i++]);
        }
    }
    
    free(linhaCopia);
}

void inserirArrayShow(Show* show) {
    if (listaSize < MAX_ARRAY) {
        lista[listaSize++] = show;
    }
}

const char* checarTodos(const char* titulo) {
    for (int i = 0; i < listaSize; i++) {
        comparacoes++;
        // Comparação exata, incluindo possíveis aspas
        if (strcmp(lista[i]->title, titulo) == 0) {
            return "SIM";
        }
    }
    return "NAO";
}

void criarLog() {
    clock_t tempoFinal = clock();
    double tempoTotal = ((double)(tempoFinal - tempoInicial)) / CLOCKS_PER_SEC * 1000;
    
    FILE* file = fopen("844412_sequencial.txt", "w");
    if (file != NULL) {
        fprintf(file, "%s\t%.0f\t%d\n", matricula, tempoTotal, comparacoes);
        fclose(file);
    } else {
        printf("Falha ao criar log\n");
    }
}

int main() {
    tempoInicial = clock();
    const char* arquivo = "/tmp/disneyplus.csv";
    
    // Primeira fase: ler os IDs e carregar os shows
    char entrada[MAX_STRING];
    scanf("%[^\n]%*c", entrada);
    
    while (strcmp(entrada, "FIM") != 0) {
        // Extrai apenas o número da entrada (ignorando o 's' inicial)
        int posicao = 0;
        sscanf(entrada + 1, "%d", &posicao); // Pula o primeiro caractere 's'
        
        FILE* file = fopen(arquivo, "r");
        if (file == NULL) {
            printf("Erro ao abrir o arquivo\n");
            return 1;
        }
        
        char linha[MAX_STRING * 10];
        int linhaAtual = 0;
        while (fgets(linha, sizeof(linha), file) != NULL) {
            if (linhaAtual == posicao) {
                linha[strcspn(linha, "\n")] = '\0';
                
                Show* show = createShow();
                lerShow(show, linha);
                inserirArrayShow(show);
                break;
            }
            linhaAtual++;
        }
        fclose(file);
        
        scanf("%[^\n]%*c", entrada);
    }
    
    // Segunda fase: verificar os títulos
    char titulo[MAX_STRING];
    
    // Lê o primeiro título antes do loop
    scanf("%[^\n]%*c", titulo);
    char tituloLimpo[MAX_STRING];
    strcpy(tituloLimpo, titulo);
    if (tituloLimpo[0] == '"') {
        memmove(tituloLimpo, tituloLimpo + 1, strlen(tituloLimpo));
        tituloLimpo[strlen(tituloLimpo) - 1] = '\0';
    }
    
    printf("%s\n", checarTodos(tituloLimpo));
    // Processa enquanto não for FIM
    while (strcmp(titulo, "FIM") != 0) {
        // Remove possíveis aspas do título
        char tituloLimpo[MAX_STRING];
        strcpy(tituloLimpo, titulo);
        if (tituloLimpo[0] == '"') {
            memmove(tituloLimpo, tituloLimpo + 1, strlen(tituloLimpo));
            tituloLimpo[strlen(tituloLimpo) - 1] = '\0';
        }
        
        printf("%s\n", checarTodos(tituloLimpo));
        
        // Lê o próximo título
        scanf("%[^\n]%*c", titulo);
    }
    
    criarLog();
    
    // Liberar memória
    for (int i = 0; i < listaSize; i++) {
        freeShow(lista[i]);
    }
    
    return 0;
}