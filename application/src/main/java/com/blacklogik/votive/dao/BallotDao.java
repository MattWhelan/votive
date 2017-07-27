package com.blacklogik.votive.dao;

import com.blacklogik.votive.api.Ballot;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BallotDao {
    private static final String INDEX = "ballots";
    private static final String BALLOT = "ballot";

    private Client client;
    private ObjectMapper objectMapper;

    public BallotDao(Client client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public Ballot create(Ballot ballot) {
        try {
            byte[] json = objectMapper.writeValueAsBytes(ballot);
            client.prepareIndex(INDEX, BALLOT, ballot.getId().toString())
                    .setSource(json, XContentType.JSON);

            return ballot;
        } catch (JsonProcessingException e) {
            throw new DaoException("Error serializing ballot", e);
        }
    }

    public List<Ballot> getBallotsForElection(UUID electionId){
        try {
            SearchResponse searchResponse = client.prepareSearch(INDEX)
                    .setTypes(BALLOT)
                    .setQuery(QueryBuilders.termQuery("electionId", electionId.toString()))
                    .setFrom(0)
                    .setSize(9000)
                    .get();
            List<Ballot> ret = new ArrayList<>();
            for (SearchHit hit : searchResponse.getHits()) {
                Ballot ballot = objectMapper.readValue(hit.getSourceAsString(), Ballot.class);
                ret.add(ballot);
            }
            return ret;
        } catch (IOException e) {
            throw new DaoException("Problem deserializing results", e);
        }
    }

    public void createIndex() {
        try {
            CreateIndexResponse createIndexResponse = client.admin()
                    .indices()
                    .prepareCreate(INDEX)
                    .addMapping(BALLOT, JsonXContent.contentBuilder()
                            .startObject("_all").field("enabled", false).endObject()
                            .startObject("properties")

                            .startObject("id").field("type", "keyword").endObject()
                            .startObject("electionId").field("type", "keyword").endObject()
                            .startObject("created").field("type", "date").endObject()
                            .startObject("lastModified").field("type", "date").endObject()
                            .startObject("votes").field("type", "object").endObject()

                            .endObject()
                    )
                    .get();
        } catch (IOException e) {
            throw new DaoException("Exception while creating index", e);
        }
    }
}
