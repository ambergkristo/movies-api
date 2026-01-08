package https.gitea.kood.tech.kristoamberg.movies_api.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
