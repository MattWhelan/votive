package com.blacklogik.votive.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Election {
    private UUID id;
    private ZonedDateTime created;
    private ZonedDateTime lastModified;
    private Style style;
    private String name;
    private String description;
    private State state;
    private List<Issue> issues;

    @JsonCreator
    public Election(@JsonProperty("id") UUID id,
                    @JsonProperty("created") ZonedDateTime created,
                    @JsonProperty("lastModified") ZonedDateTime lastModified,
                    @JsonProperty("style") Style style,
                    @JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("state") State state,
                    @JsonProperty("issues") List<Issue> issues) {
        this.id = id;
        this.created = created;
        this.lastModified = lastModified;
        this.style = style;
        this.name = name;
        this.description = description;
        this.state = state;
        this.issues = issues;
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

    public Style getStyle() {
        return style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Issue> getIssues() {
        return new ArrayList<>(issues);
    }

    public void setIssues(List<Issue> issues) {
        this.issues = new ArrayList<>(issues);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Election election = (Election) o;

        if (id != null ? !id.equals(election.id) : election.id != null) return false;
        if (created != null ? !created.equals(election.created) : election.created != null) return false;
        if (lastModified != null ? !lastModified.equals(election.lastModified) : election.lastModified != null)
            return false;
        if (style != election.style) return false;
        if (name != null ? !name.equals(election.name) : election.name != null) return false;
        if (description != null ? !description.equals(election.description) : election.description != null)
            return false;
        if (state != election.state) return false;
        return issues != null ? issues.equals(election.issues) : election.issues == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (style != null ? style.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (issues != null ? issues.hashCode() : 0);
        return result;
    }

    public enum State {
        NOMINATING, VOTING, CLOSED;
    }

    public enum Style {
        RATING;
    }
}
