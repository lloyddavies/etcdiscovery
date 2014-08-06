package net.lloyddavies.etcdiscovery.etcd;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EtcdNode {
    private String key;
    private String value;
    private List<EtcdNode> nodes;

    public EtcdNode() {
    }

    public EtcdNode(List<EtcdNode> nodes) {
        this.nodes = nodes;
    }

    public EtcdNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public List<EtcdNode> getNodes() {
        return nodes;
    }
}
