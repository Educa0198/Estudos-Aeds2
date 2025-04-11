
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Show {
    private String showId;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private Date dateAdded;
    private int releaseYear;
    private String rating;
    private String duration;
    private String[] listedIn;

    // Construtor padrão
    public Show() {
        this.showId = "NaN";
        this.type = "NaN";
        this.title = "NaN";
        this.director = "NaN";
        this.cast = new String[] { "NaN" };
        this.country = "NaN";
        this.dateAdded = null;
        this.releaseYear = 0;
        this.rating = "NaN";
        this.duration = "NaN";
        this.listedIn = new String[] { "NaN" };
    }

    // Construtor completo
    public Show(String showId, String type, String title, String director, String[] cast,
            String country, Date dateAdded, int releaseYear, String rating,
            String duration, String[] listedIn) {
        this.showId = showId;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.dateAdded = dateAdded;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.duration = duration;
        this.listedIn = listedIn;
    
    }

    // --- Getters ---
    public String getShowId() {
        return showId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String[] getCast() {
        return cast;
    }

    public String getCountry() {
        return country;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public String getDuration() {
        return duration;
    }

    public String[] getListedIn() {
        return listedIn;
    }

    // --- Setters ---
    public void setShowId(String showId) {
        if (showId == null || showId.isEmpty()) {
            this.showId = "NaN";
        } else {
            this.showId = showId;
        }
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            this.type = "NaN";
        } else {
            this.type = type;
        }
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            this.title = "NaN";
        } else {
            this.title = title;
        }
    }

    public void setDirector(String director) {
        if (director == null || director.isEmpty()) {
            this.director = "NaN";
        } else {
            this.director = director;
        }
    }

    public void setCast(String[] cast) {
        if (cast == null || cast.length == 0) {
            this.cast = new String[] { "NaN" };
        } else {
            Arrays.sort(cast);
            this.cast = cast;
        }
    }

    public void setCountry(String country) {
        if (country == null || country.isEmpty()) {
            this.country = "NaN";
        } else {
            this.country = country;
        }
    }

    public void setDateAdded(Date dateAdded) {
        if (dateAdded == null) {
            this.dateAdded = null;
        } else {
            this.dateAdded = dateAdded;
        }
    }

    public void setReleaseYear(int releaseYear) {
        if (releaseYear <= 0) {
            this.releaseYear = -1;
        } else {
            this.releaseYear = releaseYear;
        }
    }

    public void setRating(String rating) {
        if (rating == null || rating.isEmpty()) {
            this.rating = "NaN";
        } else {
            this.rating = rating;
        }
    }

    public void setDuration(String duration) {
        if (duration == null || duration.isEmpty()) {
            this.duration = "NaN";
        } else {
            this.duration = duration;
        }
    }

    public void setListedIn(String[] listedIn) {
        if (listedIn == null || listedIn.length == 0) {
            this.listedIn = new String[] { "NaN" };
        } else {
            this.listedIn = listedIn;
        }
    }


    // --- Clone ---
    @Override
    public Show clone() {
        return new Show(
                this.showId,
                this.type,
                this.title,
                this.director,
                this.cast.clone(),
                this.country,
                (this.dateAdded != null ? (Date) this.dateAdded.clone() : null),
                this.releaseYear,
                this.rating,
                this.duration,
                this.listedIn.clone());
    }

    // --- Imprimir ---
    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        String data = (dateAdded != null) ? sdf.format(dateAdded) : "NaN";

        System.out.println("=> " + getShowId() + " ## " + getTitle() + " ## " + getType() + " ## " + getDirector()
                + " ## " + Arrays.toString(getCast()) + " ## " + getCountry() + " ## " + data
                + " ## " + getReleaseYear() + " ## " + getRating() + " ## " + getDuration()
                + " ## " + Arrays.toString(getListedIn()) + " ##");
    }

    // --- Ler ---
    public void ler(String linha) {
        String[] partes = linha.split(",");
        setShowId(partes[0]);
        setType(partes[1]);
        setTitle(partes[2]);
        setDirector(partes[3]);

        // Cast
        int i = 4;
        if (partes[4].startsWith("\"")) {
            StringBuilder cast = new StringBuilder();
            cast.append(partes[i]);
            i++;

            while (i < partes.length && !partes[i].endsWith("\"")) {
                cast.append(",").append(partes[i]);
                i++;
            }
            if (i < partes.length) {
                
                cast.append(",").append(partes[i]);
                i++;
            }
            String castString = cast.toString();
            castString = castString.replaceAll("\"", "");
            String[] castSeparado = castString.split(",");
            for (int j = 0; j < castSeparado.length; j++) {
                castSeparado[j] = castSeparado[j].trim();
            }
            setCast(castSeparado);
        }
        else {i++;}

        //country
        setCountry(partes[i]);
        i++;
        
        //date
        try{
            StringBuilder datanova = new StringBuilder();
            datanova.append(partes[i]);
            datanova.append(", ");
            i++;
            datanova.append(partes[i]);
            String dataString = datanova.toString();
            dataString = dataString.replaceAll("\"","");
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            Date data = sdf.parse(dataString);
            setDateAdded(data);

        }
        catch (ParseException e)
        {
            setDateAdded(null);
        }
        i++;
        

        //releaseYear
        try {
            int anoLancamento = Integer.parseInt(partes[i]);
            setReleaseYear(anoLancamento);
        } catch (NumberFormatException e) {
            setReleaseYear(-1);
        }
        i++;
        

        //rating
        setRating(partes[i]);
        i++;

        //duration
        setDuration(partes[i]);
        i++;

        //listed_in
        if (partes[i].startsWith("\"")) {
            StringBuilder listed = new StringBuilder();
            listed.append(partes[i]);
            i++;

            while (i < partes.length && !partes[i].endsWith("\"")) {
                listed.append(",").append(partes[i]);
                i++;
            }
            if (i < partes.length) {
                
                listed.append(",").append(partes[i]);
                i++;
            }
            String ListedString = listed.toString();
            ListedString = ListedString.replaceAll("\"", "");
            String[] ListedSeparado = ListedString.split(",");
            for (int j = 0; j < ListedSeparado.length; j++) {
                ListedSeparado[j] = ListedSeparado[j].trim();
            }
            setListedIn(ListedSeparado);
        }

    }

    public static void main(String[] args) {
        String arquivo = "/home/educa/Desktop/Github/Estudos-Aeds2/tps/tp2/disneyplus.csv";
        Scanner sc = new Scanner(System.in);
        String escolherLinha = sc.nextLine();

        while (!escolherLinha.equals("FIM")) {
            // Extrai apenas os números da entrada
            String sonumero = escolherLinha.replaceAll("\\D", "");

            try {
                int posicao = Integer.parseInt(sonumero);

                BufferedReader br = new BufferedReader(new FileReader(arquivo));
                String linha = null;

                // Lê até a linha desejada
                for (int i = 0; i <= posicao; i++) {
                    linha = br.readLine();
                    if (linha == null)
                        break; // caso o arquivo acabe antes
                }

                br.close();

                if (linha != null) {
                    Show show = new Show();
                    show.ler(linha);
                    show.imprimir();
                } else {
                    System.out.println("Linha não encontrada.");
                }

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Digite algo como s123.");
            }

            escolherLinha = sc.nextLine();
        }

        sc.close();
    }
}
