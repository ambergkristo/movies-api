package https.gitea.kood.tech.kristoamberg.movies_api.controller;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.service.ActorService;
import https.gitea.kood.tech.kristoamberg.movies_api.dto.ActorPatchDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> getAll(@RequestParam(required = false) String name) {
        return actorService.getAll(name);
    }

    @GetMapping("/{id}")
    public Actor getById(@PathVariable Long id) {
        return actorService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Actor create(@Valid @RequestBody Actor actor) {
        return actorService.create(actor);
    }

    @PatchMapping("/{id}")
    public Actor update(@PathVariable Long id,
                        @RequestBody ActorPatchDto dto) {
        return actorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        actorService.delete(id);
    }
}
