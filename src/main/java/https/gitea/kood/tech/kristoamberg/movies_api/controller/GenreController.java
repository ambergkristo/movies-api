package https.gitea.kood.tech.kristoamberg.movies_api.controller;

import https.gitea.kood.tech.kristoamberg.movies_api.dto.GenrePatchDto;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import https.gitea.kood.tech.kristoamberg.movies_api.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public Genre getById(@PathVariable Long id) {
        return genreService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Genre create(@Valid @RequestBody Genre genre) {
        return genreService.create(genre);
    }

    @PatchMapping("/{id}")
    public Genre update(@PathVariable Long id, @RequestBody GenrePatchDto dto) {
        return genreService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }
}
