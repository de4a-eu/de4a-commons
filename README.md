# de4a-commons

Shared stuff for DE4A software components.

Last release: **0.1.13**

## History

* v0.1.13 - 2022-08-18
    * Updated to Apache Http Client v5.x
    * Moved the Iteration 1 schemas from `schemas/*` to `schemas/it1/*` to avoid issues with Iteration 2
* v0.1.12 - 2022-07-14
    * Updated dependencies to the latest versions
* v0.1.11 - 2021-08-08
    * Renamed Moving Abroad version "0.1.6a" to "0.1.6b"
* v0.1.9 - 2021-08-08
    * Updated to Moving Abroad 0.1.6a model
* v0.1.8 - 2021-06-23
    * Smaller deployment of de4a-iem module
* v0.1.7 - 2021-06-23
    * Downgraded to Java 1.8 because there is no need for Java 11
* v0.1.6 - 2021-05-28
    * Added `DE4AMarshaller.deUsiRedirect(Request|Response)Marshaller`
* v0.1.5 - 2021-05-27
    * XSD update for quick redirect XMLs
    * Added marshallers for Moving Abroad data types
* v0.1.4 - 2021-05-25
    * XSD update for canonical evidence types
    * The package for the Core Vocabularies 1.1 as used by the DBA pilot was changed from `eu.de4a.iem.jaxb.w3.cv.*` to `eu.de4a.iem.jaxb.w3.cv11.*`
    * An initial version of the Moving Abroad XSDs are contained (but no wrappers yet)
* v0.1.3 - 2021-05-05
    * XSD update for USI pattern
* v0.1.2 - 2021-05-05
    * XSD updates for Studying Abroad 2021-04-13

## Building

Prerequisites
* Java 1.8 or later
* Apache Maven 3.6 or later for building

To build the latest version:

```shell
mvn clean install
```

and you receive a set of JAR files in the respective submodule `target` directory.

## Modules

* **de4a-kafka-client**: a small wrapper to send messages to the shared Kafka instance for remote debugging
* **de4a-iem**: a wrapper around the DE4A IEM (Information Exchange Model)
