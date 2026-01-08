package https.gitea.kood.tech.kristoamberg.movies_api.service;

import https.gitea.kood.tech.kristoamberg.movies_api.dto.MoviePatchDto;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.exception.ResourceNotFoundException;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.ActorRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.GenreRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository movieRepository,
                        GenreRepository genreRepository,
                        ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
    }

    public List<Movie> getAll(Long genre, Integer year, Long actor) {

        System.out.println("MOVIES IN DB: " + movieRepository.count());

        return movieRepository.findAll().stream()
                .filter(m -> genre == null ||
                        m.getGenres().stream().anyMatch(g -> g.getId().equals(genre)))
                .filter(m -> year == null || year.equals(m.getReleaseYear()))
                .filter(m -> actor == null ||
                        m.getActors().stream().anyMatch(a -> a.getId().equals(actor)))
                .toList();
    }



    public Movie getById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }

    public Movie create(Movie movie, Set<Long> genreIds, Set<Long> actorIds) {
        movie.setGenres(Set.copyOf(
                genreRepository.findAllById(genreIds)
        ));
        movie.setActors(Set.copyOf(
                actorRepository.findAllById(actorIds)
        ));
        return movieRepository.save(movie);
    }

    public Movie update(Long id, MoviePatchDto dto) {
        Movie movie = getById(id);

        if (dto.getTitle() != null) {
            movie.setTitle(dto.getTitle());
        }
        if (dto.getReleaseYear() != null) {
            movie.setReleaseYear(dto.getReleaseYear());
        }
        if (dto.getDuration() != null) {
            movie.setDuration(dto.getDuration());
        }
        if (dto.getGenreIds() != null) {
            movie.setGenres(Set.copyOf(
                    genreRepository.findAllById(dto.getGenreIds())
            ));
        }
        if (dto.getActorIds() != null) {
            movie.setActors(Set.copyOf(
                    actorRepository.findAllById(dto.getActorIds())
            ));
        }

        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.delete(getById(id));
    }

    public List<Actor> getActors(Long id) {
        return List.copyOf(getById(id).getActors());
    }
}
