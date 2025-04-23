#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
#include <stdbool.h>

#define MAX_STRING_LENGTH 500
#define MAX_ARRAY_SIZE 50

typedef struct {
    char showId[MAX_STRING_LENGTH];
    char type[MAX_STRING_LENGTH];
    char title[MAX_STRING_LENGTH];
    char director[MAX_STRING_LENGTH];
    char cast[MAX_ARRAY_SIZE][MAX_STRING_LENGTH];
    int castSize;
    char country[MAX_STRING_LENGTH];
    struct tm dateAdded;
    bool hasDate;
    int releaseYear;
    char rating[MAX_STRING_LENGTH];
    char duration[MAX_STRING_LENGTH];
    char listedIn[MAX_ARRAY_SIZE][MAX_STRING_LENGTH];
    int listedInSize;
} Show;

void trim(char *str) {
    int start = 0, end = strlen(str) - 1;
    while (isspace((unsigned char)str[start])) start++;
    while (end >= start && isspace((unsigned char)str[end])) end--;
    
    for (int i = 0; i <= end - start; i++) {
        str[i] = str[start + i];
    }
    str[end - start + 1] = '\0';
}

void splitString(const char *input, const char *delimiter, char output[][MAX_STRING_LENGTH], int *count) {
    char *token;
    char *inputCopy = strdup(input);
    *count = 0;
    
    token = strtok(inputCopy, delimiter);
    while (token != NULL && *count < MAX_ARRAY_SIZE) {
        strcpy(output[*count], token);
        trim(output[*count]);
        (*count)++;
        token = strtok(NULL, delimiter);
    }
    
    free(inputCopy);
}

void initShow(Show *show) {
    strcpy(show->showId, "NaN");
    strcpy(show->type, "NaN");
    strcpy(show->title, "NaN");
    strcpy(show->director, "NaN");
    strcpy(show->cast[0], "NaN");
    show->castSize = 1;
    strcpy(show->country, "NaN");
    memset(&show->dateAdded, 0, sizeof(struct tm));
    show->hasDate = false;
    show->releaseYear = -1;
    strcpy(show->rating, "NaN");
    strcpy(show->duration, "NaN");
    strcpy(show->listedIn[0], "NaN");
    show->listedInSize = 1;
}

void setShowId(Show *show, const char *showId) {
    if (showId == NULL || strlen(showId) == 0) {
        strcpy(show->showId, "NaN");
    } else {
        strcpy(show->showId, showId);
    }
}

void setType(Show *show, const char *type) {
    if (type == NULL || strlen(type) == 0) {
        strcpy(show->type, "NaN");
    } else {
        strcpy(show->type, type);
    }
}

void setTitle(Show *show, const char *title) {
    if (title == NULL || strlen(title) == 0) {
        strcpy(show->title, "NaN");
    } else {
        strcpy(show->title, title);
    }
}

void setDirector(Show *show, const char *director) {
    if (director == NULL || strlen(director) == 0) {
        strcpy(show->director, "NaN");
    } else {
        strcpy(show->director, director);
    }
}

void setCast(Show *show, const char *castStr) {
    if (castStr == NULL || strlen(castStr) == 0) {
        strcpy(show->cast[0], "NaN");
        show->castSize = 1;
    } else {
        splitString(castStr, ",", show->cast, &show->castSize);
        
        for (int i = 0; i < show->castSize - 1; i++) {
            for (int j = 0; j < show->castSize - i - 1; j++) {
                if (strcmp(show->cast[j], show->cast[j+1]) > 0) {
                    char temp[MAX_STRING_LENGTH];
                    strcpy(temp, show->cast[j]);
                    strcpy(show->cast[j], show->cast[j+1]);
                    strcpy(show->cast[j+1], temp);
                }
            }
        }
    }
}

void setCountry(Show *show, const char *country) {
    if (country == NULL || strlen(country) == 0) {
        strcpy(show->country, "NaN");
    } else {
        strcpy(show->country, country);
    }
}

void setDateAdded(Show *show, const char *dateStr) {
    if (dateStr == NULL || strlen(dateStr) == 0) {
        show->hasDate = false;
    } else {
        if (strptime(dateStr, "%B %d, %Y", &show->dateAdded) != NULL) {
            show->hasDate = true;
        } else {
            show->hasDate = false;
        }
    }
}

void setReleaseYear(Show *show, const char *yearStr) {
    if (yearStr == NULL || strlen(yearStr) == 0) {
        show->releaseYear = -1;
    } else {
        show->releaseYear = atoi(yearStr);
        if (show->releaseYear <= 0) {
            show->releaseYear = -1;
        }
    }
}

void setRating(Show *show, const char *rating) {
    if (rating == NULL || strlen(rating) == 0) {
        strcpy(show->rating, "NaN");
    } else {
        strcpy(show->rating, rating);
    }
}

void setDuration(Show *show, const char *duration) {
    if (duration == NULL || strlen(duration) == 0) {
        strcpy(show->duration, "NaN");
    } else {
        strcpy(show->duration, duration);
    }
}

void setListedIn(Show *show, const char *listedInStr) {
    if (listedInStr == NULL || strlen(listedInStr) == 0) {
        strcpy(show->listedIn[0], "NaN");
        show->listedInSize = 1;
    } else {
        splitString(listedInStr, ",", show->listedIn, &show->listedInSize);
    }
}

void parseLine(Show *show, const char *line) {
    char buffer[MAX_STRING_LENGTH * 10];
    strcpy(buffer, line);
    
    char *tokens[20];
    int tokenCount = 0;
    char *ptr = buffer;
    bool inQuotes = false;
    char *start = buffer;
    
    for (; *ptr; ptr++) {
        if (*ptr == '"') {
            inQuotes = !inQuotes;
        } else if (*ptr == ',' && !inQuotes) {
            *ptr = '\0';
            tokens[tokenCount++] = start;
            start = ptr + 1;
        }
    }
    tokens[tokenCount++] = start;
    
    for (int i = 0; i < tokenCount; i++) {
        if (tokens[i][0] == '"') {
            memmove(tokens[i], tokens[i]+1, strlen(tokens[i]));
            tokens[i][strlen(tokens[i])-1] = '\0';
        }
        trim(tokens[i]);
    }
    
    setShowId(show, tokenCount > 0 ? tokens[0] : "");
    setType(show, tokenCount > 1 ? tokens[1] : "");
    setTitle(show, tokenCount > 2 ? tokens[2] : "");
    setDirector(show, tokenCount > 3 ? tokens[3] : "");
    setCast(show, tokenCount > 4 ? tokens[4] : "");
    setCountry(show, tokenCount > 5 ? tokens[5] : "");
    setDateAdded(show, tokenCount > 6 ? tokens[6] : "");
    setReleaseYear(show, tokenCount > 7 ? tokens[7] : "");
    setRating(show, tokenCount > 8 ? tokens[8] : "");
    setDuration(show, tokenCount > 9 ? tokens[9] : "");
    setListedIn(show, tokenCount > 10 ? tokens[10] : "");
}

void printShow(const Show *show) {
    printf("=> %s ## %s ## %s ## %s ## [", 
           show->showId, show->title, show->type, show->director);
    
    for (int i = 0; i < show->castSize; i++) {
        printf("%s%s", show->cast[i], (i < show->castSize - 1) ? ", " : "");
    }
    
    printf("] ## %s ## ", show->country);
    
    if (show->hasDate) {
        char dateStr[MAX_STRING_LENGTH];
        strftime(dateStr, sizeof(dateStr), "%B %d, %Y", &show->dateAdded);
        printf("%s", dateStr);
    } else {
        printf("NaN");
    }
    
    printf(" ## %d ## %s ## %s ## [", show->releaseYear, show->rating, show->duration);
    
    for (int i = 0; i < show->listedInSize; i++) {
        printf("%s%s", show->listedIn[i], (i < show->listedInSize - 1) ? ", " : "");
    }
    
    printf("] ##\n");
}

int main() {
    const char *arquivo = "/tmp/disneyplus.csv";
    char escolherLinha[MAX_STRING_LENGTH];
    
    fgets(escolherLinha, sizeof(escolherLinha), stdin);
    escolherLinha[strcspn(escolherLinha, "\n")] = '\0';
    
    while (strcmp(escolherLinha, "FIM") != 0) {
        int posicao = 0;
        for (int i = 0; escolherLinha[i]; i++) {
            if (isdigit(escolherLinha[i])) {
                posicao = posicao * 10 + (escolherLinha[i] - '0');
            }
        }
        
        FILE *file = fopen(arquivo, "r");
        if (file == NULL) {
            perror("Erro ao abrir o arquivo");
            return 1;
        }
        
        char line[MAX_STRING_LENGTH * 10];
        int currentLine = 0;
        bool found = false;
        
        while (fgets(line, sizeof(line), file)) {
            if (currentLine == posicao) {
                line[strcspn(line, "\n")] = '\0';
                
                Show show;
                initShow(&show);
                parseLine(&show, line);
                printShow(&show);
                
                found = true;
                break;
            }
            currentLine++;
        }
        
        fclose(file);
        
        if (!found) {
            printf("Linha não encontrada.\n");
        }
        
        fgets(escolherLinha, sizeof(escolherLinha), stdin);
        escolherLinha[strcspn(escolherLinha, "\n")] = '\0';
    }
    
    return 0;
}