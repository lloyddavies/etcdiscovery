package net.lloyddavies.etcdiscovery.etcd;

public class EtcdClientException extends RuntimeException {
    public EtcdClientException() {
    }

    public EtcdClientException(String message) {
        super(message);
    }

    public EtcdClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public EtcdClientException(Throwable cause) {
        super(cause);
    }

    public EtcdClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
