package com.blacklogik.votive.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Issue {
    private UUID id;
    private ZonedDateTime created;
    private ZonedDateTime lastModified;
    private String title;
    private String description;
    private List<Alternative> alternatives;

    @JsonCreator
    public Issue(
            @JsonProperty("id") UUID id,
            @JsonProperty("created") ZonedDateTime created,
            @JsonProperty("lastModified") ZonedDateTime lastModified,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("alternatives") List<Alternative> alternatives) {
        this.id = id;
        this.created = created;
        this.lastModified = lastModified;
        this.title = title;
        this.description = description;
        this.alternatives = alternatives;
    }

    public UUID getId() {
        return id;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != null ? !id.equals(issue.id) : issue.id != null) return false;
        if (created != null ? !created.equals(issue.created) : issue.created != null) return false;
        if (lastModified != null ? !lastModified.equals(issue.lastModified) : issue.lastModified != null) return false;
        if (title != null ? !title.equals(issue.title) : issue.title != null) return false;
        if (description != null ? !description.equals(issue.description) : issue.description != null) return false;
        return alternatives != null ? alternatives.equals(issue.alternatives) : issue.alternatives == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (alternatives != null ? alternatives.hashCode() : 0);
        return result;
    }
}
