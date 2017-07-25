package com.blacklogik.votive.services;

import com.blacklogik.votive.api.Election;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ElectionService {
    public Election create(Election election) {
        ZonedDateTime now = ZonedDateTime.now();
        Election ret = new Election(UUID.randomUUID(),
                now,
                now,
                Election.Style.RATING,
                election.getName(),
                election.getDescription(),
                Election.State.NOMINATING,
                election.getIssues());
        //TODO: save ret
        return ret;
    }

    public Election get(UUID electionId) {
        return null;
    }
}
