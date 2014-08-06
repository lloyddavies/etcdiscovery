package net.lloyddavies.etcdiscovery.etcd;

import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;

public class EtcdClient {
    private static final String ETCD_HOST = System.getProperty("etcd.host", "localhost");
    private static final int ETCD_PORT = Integer.valueOf(System.getProperty("etcd.port", "4001"));

    private final WebTarget target = ClientBuilder.newClient().target(new JerseyUriBuilder().scheme("http").host(ETCD_HOST).port(ETCD_PORT).path("/v2/keys/"));

    public void set(String dir, String key, String value, int ttl) {
        Form form = new Form();
        form.param("ttl", String.valueOf(ttl));
        form.param("value", value);

        Response response = target.path(dir + "/" + key).request(APPLICATION_JSON_TYPE).put(Entity.entity(form, APPLICATION_FORM_URLENCODED_TYPE));
        try {
            if (!response.getStatusInfo().getFamily().equals(SUCCESSFUL)) {
                throw new EtcdClientException("Failed to set key. Status code " + response.getStatus());
            }
        } finally {
            response.close();
        }
    }

    public EtcdResponse get(String dir) {
        return target.path(dir).request(APPLICATION_JSON_TYPE).get(EtcdResponse.class);
    }
}
