package https.gitea.kood.tech.kristoamberg.movies_api.service;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll(Long genreId, Integer year, Long actorId) {
        return movieRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (genreId != null) {
                Join<Movie, Genre> genres = root.join("genres");
                predicates.add(cb.equal(genres.get("id"), genreId));
            }

            if (actorId != null) {
                Join<Movie, Actor> actors = root.join("actors");
                predicates.add(cb.equal(actors.get("id"), actorId));
            }

            if (year != null) {
                predicates.add(cb.equal(root.get("releaseYear"), year));
            }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie create(Movie movie, Set<Long> genreIds, Set<Long> actorIds) {
        return movieRepository.save(movie);
    }

    public Movie update(Long id, Object dto) {
        return getById(id);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Actor> getActors(Long id) {
        return getById(id).getActors();
    }
}
