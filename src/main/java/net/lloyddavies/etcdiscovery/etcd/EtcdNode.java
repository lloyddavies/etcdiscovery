package net.lloyddavies.etcdiscovery.etcd;

import java.util.Date;
import java.util.List;

public class EtcdNode {
    private String key;
    private String value;
    private boolean dir;
    private List<EtcdNode> nodes;
    private Date expiration;
    private Integer ttl;
    private Integer modifiedIndex;
    private Integer createdIndex;

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

    public boolean isDir() {
        return dir;
    }

    public List<EtcdNode> getNodes() {
        return nodes;
    }

    public Date getExpiration() {
        return expiration;
    }

    public Integer getTtl() {
        return ttl;
    }

    public Integer getModifiedIndex() {
        return modifiedIndex;
    }

    public Integer getCreatedIndex() {
        return createdIndex;
    }
}
