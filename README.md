# de4a-commons

Shared stuff for DE4A software components

## Building

Prerequisites
* Java 11 or later
* Apache Maven 3.6 or later for building

To build the latest version:

```shell
mvn clean install
```

and you receive a set of JAR files in the respective submodule `target` directory.

## Modules

* **de4a-kafka-client**: a small wrapper to send messages to the shared Kafka instance for remote debugging
* **de4a-iem**: a wrapper around the DE4A IEM (Information Exchange Model)
