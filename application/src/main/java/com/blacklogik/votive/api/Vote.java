package com.blacklogik.votive.api;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Vote {
    private UUID alternativeId;
    private ZonedDateTime created;
    private int response;
}
