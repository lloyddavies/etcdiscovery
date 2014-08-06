package net.lloyddavies.etcdiscovery;

public class Service {
    private final String type;
    private final String host;
    private final int port;

    public Service(String type, String host, int port) {
        this.type = type;
        this.host = host;
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
