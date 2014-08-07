etcdiscovery
============

A java client for service discovery using etcd

Running tests:

```
docker run -d -p 4001:4001 coreos/etcd
mvn clean verify -DskipITs=false
```