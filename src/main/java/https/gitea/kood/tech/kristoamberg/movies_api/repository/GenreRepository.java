package https.gitea.kood.tech.kristoamberg.movies_api.repository;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);





}
