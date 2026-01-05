package https.gitea.kood.tech.kristoamberg.movies_api.repository;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
