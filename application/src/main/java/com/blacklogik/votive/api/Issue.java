package com.blacklogik.votive.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Issue {
    private String title;
    private String description;
    private List<Alternative> alternatives;

    @JsonCreator
    public Issue(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("alternatives") List<Alternative> alternatives) {
        this.title = title;
        this.description = description;
        this.alternatives = alternatives;
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

        if (title != null ? !title.equals(issue.title) : issue.title != null) return false;
        if (description != null ? !description.equals(issue.description) : issue.description != null) return false;
        return alternatives != null ? alternatives.equals(issue.alternatives) : issue.alternatives == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (alternatives != null ? alternatives.hashCode() : 0);
        return result;
    }
}
