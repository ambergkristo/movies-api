package https.gitea.kood.tech.kristoamberg.movies_api.dto;

import java.time.LocalDate;

public class ActorPatchDto {

    private String name;
    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
