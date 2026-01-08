package https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto;

import java.util.List;

public class TmdbMovieCreditsResponse {

    private List<TmdbPersonDto> cast;

    public List<TmdbPersonDto> getCast() {
        return cast;
    }

    public void setCast(List<TmdbPersonDto> cast) {
        this.cast = cast;
    }
}
