package https.gitea.kood.tech.kristoamberg.movies_api.repository;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
<<<<<<< HEAD
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
=======
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
>>>>>>> 56b9885 (fix: correct movie filtering with genre, actor and year (AND logic))

public interface MovieRepository extends JpaRepository<Movie, Long> {

<<<<<<< HEAD
    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
=======
    Optional<Movie> findByTitle(String title);

    // 🔍 FILTERD
    List<Movie> findByGenres_Id(Long genreId);

    List<Movie> findByActors_Id(Long actorId);

    List<Movie> findByReleaseYear(Integer releaseYear);
>>>>>>> 56b9885 (fix: correct movie filtering with genre, actor and year (AND logic))
}
