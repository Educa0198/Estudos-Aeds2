
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

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
    private String description; // novo campo

    // Construtor padrão
    public Show() {
        this.showId = "NaN";
        this.type = "NaN";
        this.title = "NaN";
        this.director = "NaN";
        this.cast = new String[]{"NaN"};
        this.country = "NaN";
        this.dateAdded = null;
        this.releaseYear = 0;
        this.rating = "NaN";
        this.duration = "NaN";
        this.listedIn = new String[]{"NaN"};
        this.description = "NaN";
    }

    // Construtor completo
    public Show(String showId, String type, String title, String director, String[] cast,
                String country, Date dateAdded, int releaseYear, String rating,
                String duration, String[] listedIn, String description) {
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
        this.description = description;
    }

    // --- Getters ---
    public String getShowId() { return showId; }
    public String getType() { return type; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String[] getCast() { return cast; }
    public String getCountry() { return country; }
    public Date getDateAdded() { return dateAdded; }
    public int getReleaseYear() { return releaseYear; }
    public String getRating() { return rating; }
    public String getDuration() { return duration; }
    public String[] getListedIn() { return listedIn; }
    public String getDescription() { return description; }

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
            this.cast = new String[]{"NaN"};
        } else {
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
            this.listedIn = new String[]{"NaN"};
        } else {
            this.listedIn = listedIn;
        }
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            this.description = "NaN";
        } else {
            this.description = description;
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
            this.listedIn.clone(),
            this.description
        );
    }

    // --- Imprimir ---
    public void imprimir() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        String data = (dateAdded != null) ? sdf.format(dateAdded) : "NaN";

        System.out.println("=> " + showId + " ## " + type + " ## " + title + " ## " + director
            + " ## " + Arrays.toString(cast) + " ## " + country + " ## " + data
            + " ## " + releaseYear + " ## " + rating + " ## " + duration
            + " ## " + Arrays.toString(listedIn) + " ## " + description);
    }

    // --- Ler ---
    public void ler(String linha) {
        String[] partes = linha.split(",");
        setShowId(partes[0]);
        setType(partes[1]);
        setTitle(partes[2]);
        setDirector(partes[3]);

        // Cast
        if (partes[4].startsWith("\"")) {
            StringBuilder cast = new StringBuilder();
            int i = 4;
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

            setCast(cast.toString().replace("\"", "").split(",\\s*"));

            // Seguindo a ordem das colunas do CSV
            setCountry(partes[i++]);
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
                setDateAdded(sdf.parse(partes[i++]));
            } catch (ParseException | ArrayIndexOutOfBoundsException e) {
                setDateAdded(null);
            }

            try {
                setReleaseYear(Integer.parseInt(partes[i++]));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                setReleaseYear(-1);
            }

            setRating(i < partes.length ? partes[i++] : "NaN");
            setDuration(i < partes.length ? partes[i++] : "NaN");

            // listedIn
            if (i < partes.length && partes[i].startsWith("\"")) {
                StringBuilder listedInStr = new StringBuilder();
                listedInStr.append(partes[i++]);
                while (i < partes.length && !partes[i].endsWith("\"")) {
                    listedInStr.append(",").append(partes[i++]);
                }
                if (i < partes.length) {
                    listedInStr.append(",").append(partes[i++]);
                }
                setListedIn(listedInStr.toString().replace("\"", "").split(",\\s*"));
            } else if (i < partes.length) {
                setListedIn(partes[i++].split(",\\s*"));
            }

            // description
            if (i < partes.length) {
                StringBuilder desc = new StringBuilder();
                if (partes[i].startsWith("\"")) {
                    desc.append(partes[i++]);
                    while (i < partes.length && !partes[i].endsWith("\"")) {
                        desc.append(",").append(partes[i++]);
                    }
                    if (i < partes.length) {
                        desc.append(",").append(partes[i++]);
                    }
                    setDescription(desc.toString().replace("\"", ""));
                } else {
                    setDescription(partes[i]);
                }
            } else {
                setDescription("NaN");
            }

        } else {
            setCast(partes[4].split(",\\s*"));
            // Se quiser completar os outros campos mesmo quando cast não tem aspas,
            // pode adicionar o restante aqui como no bloco acima.
        }
    }

    public static void main(String[] args) {
        String arquivo = "/home/educa/Desktop/Github/Estudos-Aeds2/tps/tp2/disneyplus.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean pularCabecalho = true;

            while ((linha = br.readLine()) != null) {
                if (pularCabecalho) {
                    pularCabecalho = false;
                    continue;
                }

                Show show = new Show();
                show.ler(linha);
                show.imprimir();
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
