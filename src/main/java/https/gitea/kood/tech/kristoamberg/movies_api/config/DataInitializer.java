package https.gitea.kood.tech.kristoamberg.movies_api.config;

import https.gitea.kood.tech.kristoamberg.movies_api.repository.ActorRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.GenreRepository;
import https.gitea.kood.tech.kristoamberg.movies_api.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            MovieRepository movieRepository,
            ActorRepository actorRepository,
            GenreRepository genreRepository
    ) {
        return args -> {
            // kui sul on siin loogika, jäta alles
            // kui mitte, võib ka tühjaks jätta
        };
    }
}
