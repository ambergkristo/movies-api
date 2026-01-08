package https.gitea.kood.tech.kristoamberg.movies_api.config;

import https.gitea.kood.tech.kristoamberg.movies_api.entity.Actor;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Genre;
import https.gitea.kood.tech.kristoamberg.movies_api.entity.Movie;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.ActorRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.GenreRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    public DataInitializer(
            GenreRepository genreRepository,
            ActorRepository actorRepository,
            MovieRepository movieRepository
    ) {
        this.genreRepository = genreRepository;
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) {

        // Puhastame DB (järjekord oluline)
        movieRepository.deleteAll();
        actorRepository.deleteAll();
        genreRepository.deleteAll();

        // ===== GENRES =====
        Genre action = genre("Action");
        Genre drama = genre("Drama");
        Genre sciFi = genre("Sci-Fi");
        Genre thriller = genre("Thriller");
        Genre comedy = genre("Comedy");

        genreRepository.saveAll(List.of(action, drama, sciFi, thriller, comedy));

        // ===== ACTORS (15+) =====
        Actor leo = actorRepository.save(actor("Leonardo DiCaprio", "1974-11-11"));
        Actor tomHardy = actorRepository.save(actor("Tom Hardy", "1977-09-15"));
        Actor ellen = actorRepository.save(actor("Elliot Page", "1987-02-21"));
        Actor matt = actorRepository.save(actor("Matt Damon", "1970-10-08"));
        Actor anne = actorRepository.save(actor("Anne Hathaway", "1982-11-12"));
        Actor keanu = actorRepository.save(actor("Keanu Reeves", "1964-09-02"));
        Actor carrie = actorRepository.save(actor("Carrie-Anne Moss", "1967-08-21"));
        Actor laurence = actorRepository.save(actor("Laurence Fishburne", "1961-07-30"));
        Actor christian = actorRepository.save(actor("Christian Bale", "1974-01-30"));
        Actor heath = actorRepository.save(actor("Heath Ledger", "1979-04-04"));
        Actor meryl = actorRepository.save(actor("Meryl Streep", "1949-06-22"));
        Actor al = actorRepository.save(actor("Al Pacino", "1940-04-25"));
        Actor brad = actorRepository.save(actor("Brad Pitt", "1963-12-18"));
        Actor scarlett = actorRepository.save(actor("Scarlett Johansson", "1984-11-22"));
        Actor natalie = actorRepository.save(actor("Natalie Portman", "1981-06-09"));
        Actor amy = actorRepository.save(actor("Amy Adams", "1974-08-20"));
        Actor harrison = actorRepository.save(actor("Harrison Ford", "1942-07-13"));

        // ===== MOVIES (20) =====
        movie("Inception", 2010, Set.of(action, sciFi, thriller), Set.of(leo, tomHardy, ellen));
        movie("The Matrix", 1999, Set.of(action, sciFi), Set.of(keanu, carrie, laurence));
        movie("The Dark Knight", 2008, Set.of(action, drama, thriller), Set.of(christian, heath));
        movie("Interstellar", 2014, Set.of(drama, sciFi), Set.of(matt, anne));
        movie("The Devil Wears Prada", 2006, Set.of(comedy, drama), Set.of(meryl));
        movie("Fight Club", 1999, Set.of(drama, thriller), Set.of(brad));
        movie("Se7en", 1995, Set.of(thriller, drama), Set.of(brad));
        movie("The Godfather", 1972, Set.of(drama), Set.of(al));
        movie("Heat", 1995, Set.of(action, drama), Set.of(al));
        movie("Black Swan", 2010, Set.of(drama, thriller), Set.of(natalie));
        movie("Lucy", 2014, Set.of(action, sciFi), Set.of(scarlett));
        movie("V for Vendetta", 2005, Set.of(action, thriller), Set.of(natalie));
        movie("Tenet", 2020, Set.of(action, sciFi, thriller), Set.of(anne));
        movie("Dunkirk", 2017, Set.of(drama, action), Set.of(tomHardy));
        movie("The Departed", 2006, Set.of(drama, thriller), Set.of(leo));
        movie("Shutter Island", 2010, Set.of(thriller), Set.of(leo));
        movie("The Prestige", 2006, Set.of(drama, thriller), Set.of(christian));
        movie("Joker", 2019, Set.of(drama, thriller), Set.of(brad));
        movie("Arrival", 2016, Set.of(drama, sciFi), Set.of(amy));
        movie("Blade Runner 2049", 2017, Set.of(sciFi, thriller), Set.of(harrison));
    }

    private Genre genre(String name) {
        Genre g = new Genre();
        g.setName(name);
        return g;
    }

    private Actor actor(String name, String birthDate) {
        Actor a = new Actor();
        a.setName(name);
        a.setBirthDate(LocalDate.parse(birthDate));
        return a;
    }

    private void movie(String title, int year, Set<Genre> genres, Set<Actor> actors) {
        Movie m = new Movie();
        m.setTitle(title);
        m.setReleaseYear(year);
        m.setDuration(120);
        m.setGenres(genres);
        m.setActors(actors);
        movieRepository.save(m);
    }
}
