# de4a-commons

Shared stuff for DE4A software components.

Last release: 
* Iteration 2: **0.2.5**
* Iteration 1: **0.1.11**

In the DE4A project we are differentiating between "Iteration 1" and "Iteration 2". As they are incompatible, care needs to be taken, that they are not mixed together.
The last release of Iteration 1 is `0.1.11` and the development branch (if ever needed) is https://github.com/de4a-wp5/de4a-commons/tree/iteration1

The `development` branch is used for development of Iteration 2 stuff.

## Building

Prerequisites
* Java 1.8 or later
* Apache Maven 3.6 or later for building

To build the latest version:

```shell
mvn clean install
```

and you receive a set of JAR files in the respective submodule `target` directory.

## Submodules

* **de4a-kafka-client**: a small wrapper to send messages to the shared Kafka instance for remote debugging
* **de4a-core-schemas**: a wrapper around the DE4A IEM (Information Exchange Model) core schemas
* **de4a-canonical-evidences**: a wrapper around the pilot specific canonical evidence types

## History

* v0.2.6 - work in progress
    * Changed `eu.de4a.iem.jaxb.t41.uc1.v2021_04_13` to `eu.de4a.iem.jaxb.t41.uc1.hed.v2021_04_13`
    * Changed `eu.de4a.iem.cev.de4a.t41.v2021_04_13` to `eu.de4a.iem.cev.de4a.t41`
    * Changed `eu.de4a.iem.cev.de4a.t42.v0_6` to `eu.de4a.iem.cev.de4a.t42`
    * Added support for T4.1 Secondary Education Diploma data model
    * Added support for T4.1 Disability data model
    * Added support for T4.1 Large Family data model
* v0.2.5 - 2022-05-13
    * Removed the SA UC1 data model 2021-02-11
* v0.2.4 - 2022-04-13
    * Fixed the date/time/dateTime JAXB binding for T41 and T42.
    * Changed the package names in the `de4a-canonical-evidences` submodule
* v0.2.3 - 2022-04-13
    * Renamed the submodule `de4a-iem` to `de4a-canonical-evidences`
* v0.2.2 - 2022-03-11
    * Extracted the IAL stuff into a separate project - https://github.com/de4a-wp5/ial-service
    * Extracted the Core Schemas into a separate submodule `de4a-core-schemas`
* v0.2.1 - 2021-09-16
    * Simplified the XSD structure and added a constant for the Specification Identifier
* v0.2.0 - 2021-09-01
    * This is the first version with data model changes for Iteration 2
* v0.1.11 - 2021-08-08
    * Renamed Moving Abroad version "0.1.6a" to "0.1.6b"
    * This is supposed to be the last version of Iteration 1.
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
