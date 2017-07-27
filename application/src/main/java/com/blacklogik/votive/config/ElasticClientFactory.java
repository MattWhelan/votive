package com.blacklogik.votive.config;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class ElasticClientFactory {
    private String clusterName = "elasticsearch";
    private int clusterPort = 9300;
    private List<String> seedNodes;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public int getClusterPort() {
        return clusterPort;
    }

    public void setClusterPort(int clusterPort) {
        this.clusterPort = clusterPort;
    }

    @NotNull
    @NotEmpty
    public List<String> getSeedNodes() {
        return seedNodes;
    }

    public void setSeedNodes(List<String> seedNodes) {
        this.seedNodes = seedNodes;
    }

    public PreBuiltTransportClient build() throws UnknownHostException {
        PreBuiltTransportClient ret = new PreBuiltTransportClient(
                Settings.builder()
                        .put("client.transport.sniff", true)
                        .put("cluster.name", clusterName).build());
        for (String node : seedNodes) {
            ret.addTransportAddress(
                    new InetSocketTransportAddress(
                            InetAddress.getByName(node),
                            clusterPort));
        }
        return ret;
    }
}
