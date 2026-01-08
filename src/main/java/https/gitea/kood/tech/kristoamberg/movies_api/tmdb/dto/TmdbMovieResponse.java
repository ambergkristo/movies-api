package https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto;

import java.util.List;

public class TmdbMovieResponse {

    private List<TmdbMovieDto> results;

    public List<TmdbMovieDto> getResults() {
        return results;
    }
}
