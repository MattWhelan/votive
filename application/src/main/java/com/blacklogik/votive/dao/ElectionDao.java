package com.blacklogik.votive.dao;

import com.blacklogik.votive.api.Election;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContent;

import java.io.IOException;
import java.util.UUID;

public class ElectionDao {
    private static final String INDEX = "elections";
    private static final String ELECTION = "election";

    private Client client;
    private ObjectMapper objectMapper;

    public ElectionDao(Client client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public Election create(Election election) {
        try {
            byte[] json = objectMapper.writeValueAsBytes(election);
            String id = election.getId().toString();
            IndexResponse response = client.prepareIndex(INDEX, ELECTION, id)
                    .setSource(json, XContentType.JSON)
                    .get();

            if (response.status().getStatus() < 300) {
                throw new DaoException("Create Election failed with response" + response.toString());
            }

            election.setVersion(response.getVersion());
            return election;
        } catch (JsonProcessingException e) {
            throw new DaoException("Serialization failed", e);
        }
    }

    public Election update(Election election) {
        try {
            byte[] json = objectMapper.writeValueAsBytes(election);
            String id = election.getId().toString();
            UpdateResponse response = client.prepareUpdate(INDEX, ELECTION, id)
                    .setDoc(json, XContentType.JSON)
                    .setVersion(election.getVersion())
                    .get();

            if (response.status().getStatus() < 300) {
                throw new DaoException("Indexing failed with response" + response.toString());
            }

            Election ret = objectMapper.readValue(response.getGetResult().source(), Election.class);
            ret.setVersion(response.getVersion());
            return ret;
        } catch (IOException e) {
            throw new DaoException("Serialization failed", e);
        }
    }

    public Election get(UUID id) {
        try {
            GetResponse getResponse = client.prepareGet(INDEX, ELECTION, id.toString())
                    .get();
            byte[] bytes = getResponse
                    .getSourceAsBytes();
            Election election = objectMapper.readValue(bytes, Election.class);
            election.setVersion(getResponse.getVersion());
            return election;
        } catch (IOException e) {
            throw new DaoException("Parsing object failed", e);
        }
    }

    public void createIndex() {
        try {
            CreateIndexResponse createIndexResponse = client.admin()
                    .indices()
                    .prepareCreate(INDEX)
                    .addMapping(ELECTION, JsonXContent.contentBuilder()
                            .startObject("_all").field("enabled", false).endObject()
                            .startObject("properties")

                            .startObject("id").field("type", "keyword").endObject()
                            .startObject("created").field("type", "date").endObject()
                            .startObject("lastModified").field("type", "date").endObject()
                            .startObject("style").field("type", "keyword").endObject()
                            .startObject("name").field("type", "keyword").endObject()
                            .startObject("description")
                                .field("type", "text")
                                .field("indexed", false)
                            .endObject()
                            .startObject("state").field("type", "keyword").endObject()
                            .startObject("issues").field("type", "nested").endObject()

                            .endObject()
                    )
                    .get();
        } catch (IOException e) {
            throw new DaoException("Exception while creating index", e);
        }
    }
}
