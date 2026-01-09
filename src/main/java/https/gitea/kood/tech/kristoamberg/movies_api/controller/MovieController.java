package https.gitea.kood.tech.kristoamberg.movies_api.repository;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitle(String title);

    // 🔍 FILTERD
    List<Movie> findByGenres_Id(Long genreId);

    List<Movie> findByActors_Id(Long actorId);

    List<Movie> findByReleaseYear(Integer releaseYear);
}
