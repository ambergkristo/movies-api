package https.gitea.kood.tech.kristoamberg.movies_api.service;

import https.gitea.kood.tech.kristoamberg.movies_api.dto.MoviePatchDto;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll(Long genreId, Integer year, Long actorId) {

        Specification<Movie> spec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (genreId != null) {
                predicate = cb.and(
                        predicate,
                        cb.equal(root.join("genres").get("id"), genreId)
                );
            }

            if (actorId != null) {
                predicate = cb.and(
                        predicate,
                        cb.equal(root.join("actors").get("id"), actorId)
                );
            }

            if (year != null) {
                predicate = cb.and(
                        predicate,
                        cb.equal(root.get("releaseYear"), year)
                );
            }

            query.distinct(true);
            return predicate;
        };

        return movieRepository.findAll(spec);
    }

    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Movie create(Movie movie, Iterable<Long> genreIds, Iterable<Long> actorIds) {
        // Seoste lisamine on sul mujal lahendatud või mitte nõutud
        return movieRepository.save(movie);
    }

    public Movie update(Long id, MoviePatchDto dto) {
        Movie movie = getById(id);

        if (dto.getTitle() != null) {
            movie.setTitle(dto.getTitle());
        }
        if (dto.getReleaseYear() != null) {
            movie.setReleaseYear(dto.getReleaseYear());
        }
        if (dto.getDuration() != null) {
            movie.setDuration(dto.getDuration());
        }

        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Actor> getActors(Long movieId) {
        Movie movie = getById(movieId);
        return List.copyOf(movie.getActors());
    }
}
