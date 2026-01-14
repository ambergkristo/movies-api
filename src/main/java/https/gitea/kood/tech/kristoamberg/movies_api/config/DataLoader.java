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
            MovieRepository movieRepository
    ) {
        return args -> {

            if (movieRepository.count() > 0) {
                return;
            }

            // -------- GENRES (5) --------
            Genre action = new Genre(); action.setName("Action");
            Genre drama = new Genre(); drama.setName("Drama");
            Genre comedy = new Genre(); comedy.setName("Comedy");
            Genre sciFi = new Genre(); sciFi.setName("Sci-Fi");
            Genre thriller = new Genre(); thriller.setName("Thriller");

            genreRepository.saveAll(List.of(action, drama, comedy, sciFi, thriller));

            // -------- ACTORS (15) --------
            Actor hanks = actor("Tom Hanks", 1956, 7, 9);
            Actor dicaprio = actor("Leonardo DiCaprio", 1974, 11, 11);
            Actor hardy = actor("Tom Hardy", 1977, 9, 15);
            Actor reeves = actor("Keanu Reeves", 1964, 9, 2);
            Actor streep = actor("Meryl Streep", 1949, 6, 22);
            Actor pitt = actor("Brad Pitt", 1963, 12, 18);
            Actor crowe = actor("Russell Crowe", 1964, 4, 7);
            Actor damon = actor("Matt Damon", 1970, 10, 8);
            Actor bale = actor("Christian Bale", 1974, 1, 30);
            Actor johansson = actor("Scarlett Johansson", 1984, 11, 22);
            Actor washington = actor("Denzel Washington", 1954, 12, 28);
            Actor winslet = actor("Kate Winslet", 1975, 10, 5);
            Actor portman = actor("Natalie Portman", 1981, 6, 9);
            Actor depp = actor("Johnny Depp", 1963, 6, 9);
            Actor lawrence = actor("Jennifer Lawrence", 1990, 8, 15);

            actorRepository.saveAll(List.of(
                    hanks, dicaprio, hardy, reeves, streep,
                    pitt, crowe, damon, bale, johansson,
                    washington, winslet, portman, depp, lawrence
            ));

            // -------- MOVIES (20) --------
            movieRepository.saveAll(List.of(
                    movie("Inception", 2010, 148, List.of(action, sciFi, thriller), List.of(dicaprio, hardy)),
                    movie("The Matrix", 1999, 136, List.of(action, sciFi), List.of(reeves)),
                    movie("Forrest Gump", 1994, 142, List.of(drama, comedy), List.of(hanks)),
                    movie("The Devil Wears Prada", 2006, 109, List.of(drama, comedy), List.of(streep)),
                    movie("Fight Club", 1999, 139, List.of(drama, thriller), List.of(pitt)),
                    movie("Gladiator", 2000, 155, List.of(action, drama), List.of(crowe)),
                    movie("The Martian", 2015, 144, List.of(sciFi, drama), List.of(damon)),
                    movie("The Dark Knight", 2008, 152, List.of(action, thriller), List.of(bale)),
                    movie("Lost in Translation", 2003, 102, List.of(drama), List.of(johansson)),
                    movie("Training Day", 2001, 122, List.of(thriller, drama), List.of(washington)),
                    movie("Titanic", 1997, 195, List.of(drama), List.of(dicaprio, winslet)),
                    movie("Black Swan", 2010, 108, List.of(thriller, drama), List.of(portman)),
                    movie("Pirates of the Caribbean", 2003, 143, List.of(action, comedy), List.of(depp)),
                    movie("Silver Linings Playbook", 2012, 122, List.of(comedy, drama), List.of(lawrence)),
                    movie("Interstellar", 2014, 169, List.of(sciFi, drama), List.of(dicaprio)),
                    movie("Django Unchained", 2012, 165, List.of(drama), List.of(dicaprio)),
                    movie("Se7en", 1995, 127, List.of(thriller), List.of(pitt)),
                    movie("Catch Me If You Can", 2002, 141, List.of(drama), List.of(dicaprio, hanks)),
                    movie("The Prestige", 2006, 130, List.of(drama, thriller), List.of(bale)),
                    movie("Gravity", 2013, 91, List.of(sciFi, thriller), List.of(johansson))
            ));
        };
    }

    // -------- HELPERS --------
    private Actor actor(String name, int y, int m, int d) {
        Actor a = new Actor();
        a.setName(name);
        a.setBirthDate(LocalDate.of(y, m, d));
        return a;
    }

    private Movie movie(
            String title,
            int year,
            int duration,
            List<Genre> genres,
            List<Actor> actors
    ) {
        Movie m = new Movie();
        m.setTitle(title);
        m.setReleaseYear(year);
        m.setDuration(duration);
        m.getGenres().addAll(genres);
        m.getActors().addAll(actors);
        return m;
    }
}
