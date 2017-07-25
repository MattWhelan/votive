package com.blacklogik.votive.api;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Ballot {
    private UUID electionId;
    private ZonedDateTime created;
    private List<Vote> votes;
}
