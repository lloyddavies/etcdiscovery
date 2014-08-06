package net.lloyddavies.etcdiscovery.etcd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EtcdResponse {
    private EtcdNode node;

    public EtcdResponse() {
    }

    public EtcdResponse(EtcdNode node) {
        this.node = node;
    }

    public EtcdNode getNode() {
        return node;
    }
}
