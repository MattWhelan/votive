package com.blacklogik.votive.resources;

import com.blacklogik.votive.api.Election;
import com.blacklogik.votive.services.ElectionService;

import javax.ws.rs.*;
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
    public Election get(@PathParam("id") UUID electionId) {
        return electionService.get(electionId);
    }

    @PUT
    @Path("/{id}")
    public Election update(@PathParam("id") UUID electionId, Election election) {
        if(!election.getId().equals(electionId)) {
            throw new BadRequestException("ID mismatch");
        }
        return electionService.update(election);
    }
}
