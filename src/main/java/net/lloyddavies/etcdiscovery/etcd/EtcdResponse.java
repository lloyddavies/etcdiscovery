package net.lloyddavies.etcdiscovery.etcd;

public class EtcdResponse {
    private String action;
    private EtcdNode node;

    public EtcdResponse() {
    }

    public EtcdResponse(EtcdNode node) {
        this.node = node;
    }

    public String getAction() {
        return action;
    }

    public EtcdNode getNode() {
        return node;
    }
}
