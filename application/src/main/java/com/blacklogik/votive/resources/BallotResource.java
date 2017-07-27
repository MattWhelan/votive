package com.blacklogik.votive.resources;

import com.blacklogik.votive.api.Ballot;
import com.blacklogik.votive.services.BallotService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/ballots")
public class BallotResource {
    private BallotService ballotService;

    public BallotResource(BallotService ballotService) {
        this.ballotService = ballotService;
    }

    @POST
    public Ballot create(Ballot ballot) {
        return ballotService.create(ballot);
    }
}
