package https.gitea.kood.tech.kristoamberg.movies_api.service;

import https.gitea.kood.tech.kristoamberg.movies_api.dto.ActorPatchDto;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.exception.BadRequestException;
import https.gitea.kood.tech.kristoamberg.movies_api.exception.ResourceNotFoundException;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<Actor> getAll(String name) {
        return name == null
                ? actorRepository.findAll()
                : actorRepository.findByNameContainingIgnoreCase(name);
    }

    public Actor getById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Actor not found with id " + id)
                );
    }

    public Actor create(Actor actor) {
        return actorRepository.save(actor);
    }

    public Actor update(Long id, ActorPatchDto dto) {
        Actor actor = getById(id);

        if (dto.getName() != null) {
            actor.setName(dto.getName());
        }

        if (dto.getBirthDate() != null) {
            actor.setBirthDate(dto.getBirthDate());
        }

        return actorRepository.save(actor);
    }

    public void delete(Long id) {
        Actor actor = getById(id);

        if (!actor.getMovies().isEmpty()) {
            throw new BadRequestException(
                    "Unable to delete actor '" + actor.getName() +
                            "' as they are associated with " + actor.getMovies().size() + " movies"
            );
        }

        actorRepository.delete(actor);
    }
}
