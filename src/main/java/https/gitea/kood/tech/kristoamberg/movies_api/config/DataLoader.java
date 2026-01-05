package https.gitea.kood.tech.kristoamberg.movies_api.config;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.ActorRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.GenreRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            GenreRepository genreRepository,
            ActorRepository actorRepository,
            MovieRepository movieRepository) {
        return args -> {

            if (movieRepository.count() > 0) {
                return;
            }

            Genre action = new Genre();
            action.setName("Action");

            Genre drama = new Genre();
            drama.setName("Drama");

            Genre sciFi = new Genre();
            sciFi.setName("Sci-Fi");

            Genre thriller = new Genre();
            thriller.setName("Thriller");

            Genre comedy = new Genre();
            comedy.setName("Comedy");

            genreRepository.saveAll(List.of(action, drama, sciFi, thriller, comedy));

            Actor dicaprio = new Actor();
            dicaprio.setName("Leonardo DiCaprio");
            dicaprio.setBirthDate(LocalDate.of(1974, 11, 11));

            Actor hardy = new Actor();
            hardy.setName("Tom Hardy");
            hardy.setBirthDate(LocalDate.of(1977, 9, 15));

            Actor streep = new Actor();
            streep.setName("Meryl Streep");
            streep.setBirthDate(LocalDate.of(1949, 6, 22));

            Actor hanks = new Actor();
            hanks.setName("Tom Hanks");
            hanks.setBirthDate(LocalDate.of(1956, 7, 9));

            Actor reeves = new Actor();
            reeves.setName("Keanu Reeves");
            reeves.setBirthDate(LocalDate.of(1964, 9, 2));

            actorRepository.saveAll(List.of(dicaprio, hardy, streep, hanks, reeves));

            Movie inception = new Movie();
            inception.setTitle("Inception");
            inception.setReleaseYear(2010);
            inception.setDuration(148);
            inception.getGenres().addAll(List.of(action, sciFi, thriller));
            inception.getActors().addAll(List.of(dicaprio, hardy));

            Movie matrix = new Movie();
            matrix.setTitle("The Matrix");
            matrix.setReleaseYear(1999);
            matrix.setDuration(136);
            matrix.getGenres().addAll(List.of(action, sciFi));
            matrix.getActors().add(reeves);

            Movie devilWearsPrada = new Movie();
            devilWearsPrada.setTitle("The Devil Wears Prada");
            devilWearsPrada.setReleaseYear(2006);
            devilWearsPrada.setDuration(109);
            devilWearsPrada.getGenres().addAll(List.of(drama, comedy));
            devilWearsPrada.getActors().add(streep);

            Movie forrestGump = new Movie();
            forrestGump.setTitle("Forrest Gump");
            forrestGump.setReleaseYear(1994);
            forrestGump.setDuration(142);
            forrestGump.getGenres().addAll(List.of(drama, comedy));
            forrestGump.getActors().add(hanks);

            movieRepository.saveAll(
                    List.of(inception, matrix, devilWearsPrada, forrestGump));
        };
    }
}
