package https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto;

public class TmdbMovieDto {

    private Long id;
    private String title;
    private String release_date;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return release_date;
    }
}
