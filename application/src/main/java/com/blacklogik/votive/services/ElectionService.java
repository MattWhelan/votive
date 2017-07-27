package com.blacklogik.votive.services;

import com.blacklogik.votive.api.Election;
import com.blacklogik.votive.dao.ElectionDao;

import java.time.ZonedDateTime;
import java.util.UUID;

public class ElectionService {
    private ElectionDao electionDao;

    public ElectionService(ElectionDao electionDao) {
        this.electionDao = electionDao;
    }

    public Election create(Election election) {
        ZonedDateTime now = ZonedDateTime.now();
        Election toCreate = new Election(UUID.randomUUID(),
                null,
                now,
                now,
                Election.Style.RATING,
                election.getName(),
                election.getDescription(),
                Election.State.NOMINATING,
                election.getIssues());

        return electionDao.create(toCreate);
    }

    public Election update(Election election) {
        election.setLastModified(ZonedDateTime.now());
        return electionDao.update(election);
    }

    public Election get(UUID electionId) {
        return electionDao.get(electionId);
    }
}
