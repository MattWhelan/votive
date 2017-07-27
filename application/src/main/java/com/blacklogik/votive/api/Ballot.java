package com.blacklogik.votive.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;

public class Ballot {
    private UUID id;
    private UUID electionId;
    private ZonedDateTime created;
    private Map<UUID, Integer> votes;

    @JsonCreator
    public Ballot(
            @JsonProperty("id") UUID id,
            @JsonProperty("electionId") UUID electionId,
            @JsonProperty("created") ZonedDateTime created,
            @JsonProperty("votes") Map<UUID, Integer> votes) {
        this.id = id;
        this.electionId = electionId;
        this.created = created;
        this.votes = votes;
    }

    public UUID getId() {
        return id;
    }

    public UUID getElectionId() {
        return electionId;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Map<UUID, Integer> getVotes() {
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ballot ballot = (Ballot) o;

        if (id != null ? !id.equals(ballot.id) : ballot.id != null) return false;
        if (electionId != null ? !electionId.equals(ballot.electionId) : ballot.electionId != null) return false;
        if (created != null ? !created.equals(ballot.created) : ballot.created != null) return false;
        return votes != null ? votes.equals(ballot.votes) : ballot.votes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (electionId != null ? electionId.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        return result;
    }
}
