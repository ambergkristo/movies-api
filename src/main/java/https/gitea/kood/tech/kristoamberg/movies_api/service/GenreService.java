package https.gitea.kood.tech.kristoamberg.movies_api.service;

import https.gitea.kood.tech.kristoamberg.movies_api.dto.GenrePatchDto;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import https.gitea.kood.tech.kristoamberg.movies_api.exception.ResourceNotFoundException;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre not found"));
    }

    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Long id, GenrePatchDto dto) {
        Genre genre = getById(id);

        if (dto.getName() != null) {
            genre.setName(dto.getName());
        }

        return genreRepository.save(genre);
    }

    public void delete(Long id) {
        genreRepository.delete(getById(id));
    }
}
