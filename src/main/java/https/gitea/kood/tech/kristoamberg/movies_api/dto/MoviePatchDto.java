package https.gitea.kood.tech.kristoamberg.movies_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoviePatchDto {

    private String title;
    private Integer releaseYear;
    private Integer duration;
    private Set<Long> genreIds;
    private Set<Long> actorIds;

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public Set<Long> getGenreIds() {
        return genreIds;
    }

    public Set<Long> getActorIds() {
        return actorIds;
    }
}
