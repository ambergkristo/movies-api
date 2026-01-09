package https.gitea.kood.tech.kristoamberg.movies_api.controller;

import https.gitea.kood.tech.kristoamberg.movies_api.dto.MoviePatchDto;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAll(
            @RequestParam(required = false) Long genreId,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long actorId
    ) {
        return movieService.getAll(genreId, year, actorId);
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(
            @Valid @RequestBody Movie movie,
            @RequestParam Set<Long> genreIds,
            @RequestParam Set<Long> actorIds
    ) {
        return movieService.create(movie, genreIds, actorIds);
    }

    @PatchMapping("/{id}")
    public Movie update(
            @PathVariable Long id,
            @RequestBody MoviePatchDto dto
    ) {
        return movieService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

    @GetMapping("/{id}/actors")
    public List<Actor> getActors(@PathVariable Long id) {
        return movieService.getActors(id);
    }
}
