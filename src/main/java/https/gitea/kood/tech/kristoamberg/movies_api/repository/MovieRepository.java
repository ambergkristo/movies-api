package https.gitea.kood.tech.kristoamberg.movies_api.repository;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

<<<<<<< HEAD
public interface MovieRepository extends JpaRepository<Movie, Long> {
=======
import java.util.Optional;

public interface MovieRepository
        extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    Optional<Movie> findByTitle(String title);
>>>>>>> 56b9885 (fix: correct movie filtering with genre, actor and year (AND logic))
}
