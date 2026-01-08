package https.gitea.kood.tech.kristoamberg.movies_api.tmdb;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.ActorRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.GenreRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.tmdb.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class TmdbImportService {

    private final TmdbClient tmdbClient;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public TmdbImportService(
            TmdbClient tmdbClient,
            MovieRepository movieRepository,
            ActorRepository actorRepository,
            GenreRepository genreRepository
    ) {
        this.tmdbClient = tmdbClient;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    // ========= MOVIES =========

    public void importMovies() {
        TmdbMovieResponse response = tmdbClient.getPopularMovies(1);
        if (response.getResults() == null) return;

        for (TmdbMovieDto dto : response.getResults()) {

            Movie movie = movieRepository
                    .findByTitle(dto.getTitle())
                    .orElseGet(Movie::new);

            movie.setTitle(dto.getTitle());

            // ---- RELEASE YEAR (UUENDAB KA OLEMASOLEVA) ----
            if (movie.getReleaseYear() == null
                    && dto.getReleaseDate() != null
                    && dto.getReleaseDate().length() >= 4) {

                movie.setReleaseYear(
                        Integer.parseInt(dto.getReleaseDate().substring(0, 4))
                );
            }

            // duration pole TMDB popular endpointis
            if (movie.getDuration() == null) {
                movie.setDuration(0);
            }

            movieRepository.save(movie);
        }
    }

    // ========= PEOPLE =========

    public void importPeople() {
        TmdbMovieResponse response = tmdbClient.getPopularMovies(1);
        if (response.getResults() == null) return;

        for (TmdbMovieDto tmdbMovie : response.getResults()) {

            Movie movie = movieRepository
                    .findByTitle(tmdbMovie.getTitle())
                    .orElse(null);

            if (movie == null) continue;

            TmdbMovieCreditsResponse credits =
                    tmdbClient.getMovieCredits(tmdbMovie.getId());

            if (credits.getCast() == null) continue;

            Set<Actor> actors = new HashSet<>();

            credits.getCast().stream()
                    .limit(5)
                    .forEach(person -> actors.add(resolveActor(person)));

            movie.setActors(actors);
            movieRepository.save(movie);
        }
    }

    // ========= GENRES =========

    public void importGenres() {
        TmdbGenreResponse response = tmdbClient.getGenres();
        if (response == null || response.getGenres() == null) return;

        response.getGenres().forEach(dto -> {
            genreRepository.findByNameIgnoreCase(dto.getName())
                    .orElseGet(() -> {
                        Genre g = new Genre();
                        g.setName(dto.getName());
                        return genreRepository.save(g);
                    });
        });
    }

    // ========= ACTOR RESOLVE (UUENDAB birthDate) =========

    private Actor resolveActor(TmdbPersonDto person) {

        Actor actor = actorRepository
                .findByNameIgnoreCase(person.getName())
                .orElseGet(() -> {
                    Actor a = new Actor();
                    a.setName(person.getName());
                    return a;
                });

        // ---- BIRTHDATE (UUENDAB KA OLEMASOLEVA) ----
        if (actor.getBirthDate() == null) {
            TmdbPersonDetailsDto details =
                    tmdbClient.getPersonDetails(person.getId());

            if (details != null && details.getBirthday() != null) {
                actor.setBirthDate(LocalDate.parse(details.getBirthday()));
            }
        }

        return actorRepository.save(actor);
    }
}
