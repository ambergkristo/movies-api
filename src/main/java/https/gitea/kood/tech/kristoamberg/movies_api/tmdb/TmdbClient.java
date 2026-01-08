package https.gitea.kood.tech.kristoamberg.movies_api.tmdb;

import https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto.TmdbGenreResponse;
import https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto.TmdbMovieCreditsResponse;
import https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto.TmdbMovieResponse;
import https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto.TmdbPersonDetailsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class TmdbClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl;
    private final String token;

    public TmdbClient(
            @Value("${tmdb.base.url}") String baseUrl,
            @Value("${tmdb.api.token}") String token
    ) {
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException("tmdb.base.url is missing");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("tmdb.api.token is missing");
        }

        this.baseUrl = baseUrl;
        this.token = token;
    }

    private <T> T get(String path, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<T> response = restTemplate.exchange(
                baseUrl + path,
                HttpMethod.GET,
                entity,
                responseType
        );

        return response.getBody();
    }

    // ========= GENRES =========

    public TmdbGenreResponse getGenres() {
        return get("/genre/movie/list", TmdbGenreResponse.class);
    }

    // ========= MOVIES =========

    public TmdbMovieResponse getPopularMovies(int page) {
        return get("/movie/popular?page=" + page, TmdbMovieResponse.class);
    }

    public TmdbMovieCreditsResponse getMovieCredits(Long movieId) {
        return get("/movie/" + movieId + "/credits", TmdbMovieCreditsResponse.class);
    }

    // ========= PEOPLE (DETAILS) =========

    public TmdbPersonDetailsDto getPersonDetails(Long personId) {
        return get("/person/" + personId, TmdbPersonDetailsDto.class);
    }
}
