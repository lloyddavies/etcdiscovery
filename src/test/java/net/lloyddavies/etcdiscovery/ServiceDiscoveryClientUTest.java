package net.lloyddavies.etcdiscovery;

import net.lloyddavies.etcdiscovery.etcd.EtcdClient;
import net.lloyddavies.etcdiscovery.etcd.EtcdNode;
import net.lloyddavies.etcdiscovery.etcd.EtcdResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceDiscoveryClientUTest {
    private static final String SERVICE_TYPE = "my-service";
    private static final String HOST = "10.0.0.1";
    private static final int PORT = 8080;

    @Mock
    private EtcdClient etcdClient;

    @InjectMocks
    private ServiceDiscoveryClient client;

    @Test
    public void register() {
        client.register(new Service(SERVICE_TYPE, HOST, PORT));
        verify(etcdClient).set(SERVICE_TYPE, HOST + ":" + PORT, HOST + ":" + PORT, ServiceDiscoveryClient.TTL);
    }

    @Test
    public void discover() {
        when(etcdClient.get(SERVICE_TYPE)).thenReturn(new EtcdResponse(
                new EtcdNode(asList(
                        new EtcdNode(HOST + ":" + PORT, HOST + ":" + PORT)
                ))
        ));

        Service service = client.discover(SERVICE_TYPE);
        assertEquals(SERVICE_TYPE, service.getType());
        assertEquals(HOST, service.getHost());
        assertEquals(PORT, service.getPort());
    }

    @Test(expected = NoSuchElementException.class)
    public void discoverNotFound() {
        when(etcdClient.get(SERVICE_TYPE)).thenReturn(new EtcdResponse(new EtcdNode()));
        client.discover(SERVICE_TYPE);
    }
}