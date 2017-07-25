package com.blacklogik.votive.resources;

import com.blacklogik.votive.api.Election;
import com.blacklogik.votive.services.ElectionService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.UUID;

@Path("/elections")
public class ElectionResource {
    private ElectionService electionService;

    public ElectionResource(ElectionService electionService) {
        this.electionService = electionService;
    }

    @POST
    public Election create(Election election) {
        return electionService.create(election);
    }

    @GET
    @Path("/{id}")
    public Election get(UUID electionId) {
        return electionService.get(electionId);
    }
}
