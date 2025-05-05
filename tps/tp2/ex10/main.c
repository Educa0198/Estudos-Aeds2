#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <ctype.h>

#define _XOPEN_SOURCE

#define MAX_STRING 500
#define MAX_ARRAY 100
#define MAX_SHOWS 1000

typedef struct {
    char showId[MAX_STRING];
    char type[MAX_STRING];
    char title[MAX_STRING];
    char director[MAX_STRING];
    char cast[MAX_ARRAY][MAX_STRING];
    int castSize;
    char country[MAX_STRING];
    char dateAdded[MAX_STRING];
    int releaseYear;
    char rating[MAX_STRING];
    char duration[MAX_STRING];
    char listedIn[MAX_ARRAY][MAX_STRING];
    int listedInSize;
} Show;

Show lista[MAX_SHOWS];
int listaSize = 0;
int comparacoes = 0;
int movimentacoes = 0;
char matricula[] = "844412";
time_t tempoInicial;

void initShow(Show *s) {
    strcpy(s->showId, "NaN");
    strcpy(s->type, "NaN");
    strcpy(s->title, "NaN");
    strcpy(s->director, "NaN");
    strcpy(s->cast[0], "NaN");
    s->castSize = 1;
    strcpy(s->country, "NaN");
    strcpy(s->dateAdded, "NaN");
    s->releaseYear = -1;
    strcpy(s->rating, "NaN");
    strcpy(s->duration, "NaN");
    strcpy(s->listedIn[0], "NaN");
    s->listedInSize = 1;
}

void trim(char *str) {
    int i = 0, j = 0;
    while (str[i] == ' ') i++;
    while (str[i] != '\0') {
        str[j++] = str[i++];
    }
    str[j] = '\0';
    while (j > 0 && str[j-1] == ' ') str[--j] = '\0';
}

void ordenarCast(Show *s) {
    // Ordena o cast
    for (int i = 0; i < s->castSize - 1; i++) {
        for (int j = i + 1; j < s->castSize; j++) {
            if (strcmp(s->cast[i], s->cast[j]) > 0) {
                char temp[MAX_STRING];
                strcpy(temp, s->cast[i]);
                strcpy(s->cast[i], s->cast[j]);
                strcpy(s->cast[j], temp);
            }
        }
    }
    
    // Remove espaços extras nos nomes
    for (int i = 0; i < s->castSize; i++) {
        trim(s->cast[i]);
    }
}

void processQuotedField(char* dest, char* src, int* index) {
    int i = *index;
    int j = 0;
    
    if (src[i] == '"') {
        i++;
        while (src[i] != '"' && src[i] != '\0') {
            dest[j++] = src[i++];
        }
        if (src[i] == '"') i++;
    } else {
        while (src[i] != ',' && src[i] != '\0') {
            dest[j++] = src[i++];
        }
    }
    
    dest[j] = '\0';
    trim(dest);
    *index = i;
}

void ler(Show *s, char *linha) {
    initShow(s);
    int i = 0;
    char buffer[MAX_STRING];
    
    // showId
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->showId, buffer);
    i++;
    
    // type
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->type, buffer);
    i++;
    
    // title
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->title, buffer);
    i++;
    
// director
processQuotedField(buffer, linha, &i);
if (strlen(buffer) > 0) {
    char *token = strtok(buffer, ",");
    strcpy(s->director, "");
    int first = 1;
    while (token != NULL) {
        trim(token);
        if (!first) {
            strcat(s->director, ", ");
        }
        strcat(s->director, token);
        token = strtok(NULL, ",");
        first = 0;
    }
} else {
    strcpy(s->director, "NaN");
}
    i++;
    
    // cast
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) {
        char *token = strtok(buffer, ",");
        s->castSize = 0;
        while (token != NULL) {
            trim(token);
            if (strlen(token) > 0) {
                strcpy(s->cast[s->castSize++], token);
            }
            token = strtok(NULL, ",");
        }
        if (s->castSize == 0) strcpy(s->cast[s->castSize++], "NaN");
        ordenarCast(s);
    }
    i++;
    
    // country
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->country, buffer);
    i++;
    
    // dateAdded
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->dateAdded, buffer);
    else strcpy(s->dateAdded, "NaN");
    i++;
    
    // releaseYear
    processQuotedField(buffer, linha, &i);
    s->releaseYear = atoi(buffer);
    i++;
    
    // rating
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->rating, buffer);
    i++;
    
    // duration
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) strcpy(s->duration, buffer);
    i++;
    
    // listedIn
    processQuotedField(buffer, linha, &i);
    if (strlen(buffer) > 0) {
        char *token = strtok(buffer, ",");
        s->listedInSize = 0;
        while (token != NULL) {
            trim(token);
            if (strlen(token) > 0) {
                strcpy(s->listedIn[s->listedInSize++], token);
            }
            token = strtok(NULL, ",");
        }
        if (s->listedInSize == 0) strcpy(s->listedIn[s->listedInSize++], "NaN");
    }
}
void imprimir(Show *s) {
    printf("=> %s ## %s ## %s ## %s ## [", s->showId, s->title, s->type, s->director);
    
    for (int i = 0; i < s->castSize; i++) {
        printf("%s", s->cast[i]);
        if (i < s->castSize - 1) printf(", ");
    }
    
    printf("] ## %s ## %s ## %d ## %s ## %s ## [", s->country, s->dateAdded, 
           s->releaseYear, s->rating, s->duration);
           
    for (int i = 0; i < s->listedInSize; i++) {
        printf("%s", s->listedIn[i]);
        if (i < s->listedInSize - 1) printf(", ");
    }
    
    printf("] ##\n");
}

time_t parseDate(const char* dateStr) {
    if (strcmp(dateStr, "NaN") == 0) return 0;
    
    struct tm tm = {0};
    if (strptime(dateStr, "%B %d, %Y", &tm) == NULL) return 0;
    
    return mktime(&tm);
}

bool compareShows(Show a, Show b) {
    comparacoes++;
    
    time_t timeA = parseDate(a.dateAdded);
    time_t timeB = parseDate(b.dateAdded);
    
    if (timeA && timeB) {
        if (timeA != timeB) {
            return timeA > timeB;
        }
    }
    else if (timeA) {
        return false;
    }
    else if (timeB) {
        return true;
    }
    
    return strcmp(a.title, b.title) > 0;
}

void swap(int i, int j) {
    Show temp = lista[i];
    lista[i] = lista[j];
    lista[j] = temp;
    movimentacoes++;
}

int partition(int esq, int dir) {
    Show pivo = lista[(esq + dir) / 2];
    int i = esq, j = dir;

    while (i <= j) {
        while (compareShows(pivo, lista[i])) i++;  // lista[i] < pivo
        while (compareShows(lista[j], pivo)) j--;  // lista[j] > pivo

        if (i <= j) {
            swap(i, j);
            i++;
            j--;
        }
    }
    return i;
}

void quickSort(int esq, int dir) {
    if (esq < dir) {
        int meio = partition(esq, dir);
        quickSort(esq, meio - 1);
        quickSort(meio, dir);
    }
}


void bubbleSort() {
    bool swapped;
    for (int i = 0; i < listaSize - 1; i++) {
        swapped = false;
        for (int j = 0; j < listaSize - i - 1; j++) {
            if (compareShows(lista[j], lista[j+1])) {
                Show temp = lista[j];
                lista[j] = lista[j+1];
                lista[j+1] = temp;
                movimentacoes++;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
}

void criarLog() {
    time_t tempoFinal = time(NULL);
    double tempoTotal = difftime(tempoFinal, tempoInicial);
    
    FILE *file = fopen("844412_bolha.txt", "w");
    if (file != NULL) {
        fprintf(file, "%s\t%.0f\t%d\t%d\n", matricula, tempoTotal, comparacoes, movimentacoes);
        fclose(file);
    }
}

int main() {
    tempoInicial = time(NULL);
    char arquivo[] = "/tmp/disneyplus.csv";
    char escolherLinha[MAX_STRING];
    
    scanf("%[^\n]%*c", escolherLinha);
    while (strcmp(escolherLinha, "FIM") != 0) {
        int posicao = 0;
        for (int i = 0; escolherLinha[i]; i++) {
            if (isdigit(escolherLinha[i])) {
                posicao = posicao * 10 + (escolherLinha[i] - '0');
            }
        }
        
        FILE *file = fopen(arquivo, "r");
        if (file == NULL) {
            printf("Erro ao abrir o arquivo.\n");
            return 1;
        }
        
        char linha[MAX_STRING * 10];
        int currentLine = 0;
        
        while (fgets(linha, sizeof(linha), file) != NULL) {
            if (currentLine == posicao) {
                linha[strcspn(linha, "\n")] = 0;
                Show show;
                ler(&show, linha);
                lista[listaSize++] = show;
                break;
            }
            currentLine++;
        }
        
        fclose(file);
        scanf("%[^\n]%*c", escolherLinha);
    }
    
    quickSort(0, listaSize - 1);

    
    for (int i = 0; i < listaSize; i++) {
        imprimir(&lista[i]);
    }
    
    criarLog();
    return 0;
}