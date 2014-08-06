package net.lloyddavies.etcdiscovery;

import net.lloyddavies.etcdiscovery.etcd.EtcdClient;
import net.lloyddavies.etcdiscovery.etcd.EtcdNode;
import net.lloyddavies.etcdiscovery.etcd.EtcdResponse;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public final class ServiceDiscoveryClient {
    protected static final int TTL = 30;

    private EtcdClient etcdClient = new EtcdClient();
    private Random random = new SecureRandom();

    public void register(Service service) {
        String key = service.getHost() + ":" + service.getPort();
        etcdClient.set(service.getType(), key, key, TTL);
    }

    public Service discover(String serviceType) {
        EtcdResponse response = etcdClient.get(serviceType);

        if (hasValue(response.getNode().getNodes())) {
            throw new NoSuchElementException("Service not found for type " + serviceType);
        }

        EtcdNode node = random(response.getNode().getNodes());

        String host = node.getValue().split(":")[0];
        int port = Integer.parseInt(node.getValue().split(":")[1]);

        return new Service(serviceType, host, port);
    }

    private boolean hasValue(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    private <T> T random(List<T> values) {
        return values.get(random.nextInt(values.size()));
    }
}
