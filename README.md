# de4a-commons

Shared stuff for DE4A software components.

Last release: 
* Iteration 2: **0.2.11**
* Iteration 1: **0.1.12**

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

* v0.2.11 - 2022-08-04
    * Added log enums to `de4a-kafka-client`
* v0.2.10 - 2022-07-07
    * Updated the HigherEducation version in `EDE4ACanonicalEvidenceType` - for the changes of 0.2.8
    * Removed the T4.3 Pension Means of Living Evidence v0.1 again
    * Removed the T4.3 Unemployment Means of Living Evidence v0.1 again
    * Removed the T4.3 Working Life Means of Living Evidence v0.1 again
* v0.2.8 - 2022-06-29
    * Updated the T4.1 HigherEducationDiploma XSD
        * Package changed from `eu.de4a.iem.jaxb.t41.higheredu.v2021_04_13` to `eu.de4a.iem.jaxb.t41.higheredu.v2022_06_23`
    * Renamed `DE4ACoreMarshaller` methods for consistency with the element names instead of the types
        * `deResponseExtractMultiEvidenceMarshaller` became `deResponseTransferEvidenceMarshaller`
        * `drRequestExtractMultiEvidenceIMMarshaller` became `drRequestTransferEvidenceIMMarshaller`
        * `drRequestExtractMultiEvidenceLUMarshaller` became `drRequestTransferEvidenceLUMarshaller`
        * `drRequestExtractMultiEvidenceUSIMarshaller` became `drRequestTransferEvidenceUSIMarshaller`
        * `dtResponseExtractMultiEvidenceMarshaller` became `dtResponseTransferEvidenceMarshaller`
        * `defResponseErrorMarshaller` became `defResponseMarshaller`
* v0.2.7 - 2022-05-31
    * Updated the `EDE4ACanonicalEvidenceType` enum
    * Changed the created T4.1 package names below `eu.de4a.iem.jaxb.t41`
* v0.2.6 - 2022-05-30
    * Changed `eu.de4a.iem.jaxb.t41.uc1.v2021_04_13` to `eu.de4a.iem.jaxb.t41.uc1.hed.v2021_04_13`
    * Changed `eu.de4a.iem.cev.de4a.t41.v2021_04_13` to `eu.de4a.iem.cev.de4a.t41`
    * Changed `eu.de4a.iem.cev.de4a.t42.v0_6` to `eu.de4a.iem.cev.de4a.t42`
    * Changed `eu.de4a.iem.cev.de4a.t43.v1_6b` to `eu.de4a.iem.cev.de4a.t43`
    * Added support for T4.1 Secondary Education Diploma data model
    * Added support for T4.1 Disability data model
    * Added support for T4.1 Large Family data model
    * Updated the T4.3 Birth Evidence to v1.7
    * Updated the T4.3 Domicile Registration Evidence to v1.7
    * Updated the T4.3 Marriage Evidence to v1.7
    * Added the T4.3 Pension Means of Living Evidence v0.1
    * Added the T4.3 Unemployment Means of Living Evidence v0.1
    * Added the T4.3 Working Life Means of Living Evidence v0.1
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
* v0.1.12 - 2022-07-14
    * Updated dependencies to the latest versions
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
