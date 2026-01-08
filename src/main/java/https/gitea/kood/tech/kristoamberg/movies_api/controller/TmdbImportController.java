package https.gitea.kood.tech.kristoamberg.movies_api.controller;

import https.gitea.kood.tech.kristoamberg.movies_api.tmdb.TmdbImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import/tmdb")
public class TmdbImportController {

    private final TmdbImportService tmdbImportService;

    public TmdbImportController(TmdbImportService tmdbImportService) {
        this.tmdbImportService = tmdbImportService;
    }

    @PostMapping("/movies")
    public ResponseEntity<?> importMovies() {
        tmdbImportService.importMovies();
        return ResponseEntity.ok("TMDB movies imported");
    }

    @PostMapping("/people")
    public ResponseEntity<?> importPeople() {
        tmdbImportService.importPeople();
        return ResponseEntity.ok("TMDB people imported");
    }

    @PostMapping("/genres")
    public ResponseEntity<String> importGenres() {
        tmdbImportService.importGenres();
        return ResponseEntity.ok("TMDB genres imported");
    }
}
