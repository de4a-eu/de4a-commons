# de4a-commons

Shared stuff for DE4A software components.

Last release: **0.1.3**

## History

* v0.1.3 - 2021-05-05
    * XSD update for USI pattern
* v0.1.2 - 2021-05-05
    * XSD updates for Studying Abroad 2021-04-13

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
