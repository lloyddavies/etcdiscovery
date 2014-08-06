package net.lloyddavies.etcdiscovery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceDiscoveryClientITest {
    private static final String SERVICE_TYPE = "my-service";
    private static final String HOST = "10.0.0.1";
    private static final int PORT = 8080;

    private final ServiceDiscoveryClient client = new ServiceDiscoveryClient();

    @Test
    public void registerAndDiscover() {
        client.register(new Service(SERVICE_TYPE, HOST, PORT));
        Service service = client.discover(SERVICE_TYPE);
        assertEquals(HOST, service.getHost());
        assertEquals(PORT, service.getPort());
    }
}