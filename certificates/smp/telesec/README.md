# DE4A SMP Production Certificates

This folder contains the certificates as well as the finally assembled truststore for the use with the CEF production PKI.

The SMP truststore contains the following certificates:
* TeleSec and TeleSec GlobalRoot are the payload signature CA certificates
* GlobalSign certificates are TLS certificates to access SMK
* Let's Encrypt certificates are TLS certificates to access Directory

The password to the trust store is `de4a`.

Updates
* 2022-05-04 "de4a-truststore-smp-v3-pw-de4a2.jks"
    * Added the "Telesec Business CA 21"
