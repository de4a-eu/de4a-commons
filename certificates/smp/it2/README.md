# DE4A SMP Production Certificates

This folder contains the certificates as well as the finally assembled truststore for the use with the CEF production PKI.

The name of the folder "it2" is misleading. It should have been called "prod", because it can be used for Iteration 1 as well.

The SMP truststore contains the following certificates:
* TeleSec and TeleSec GlobalRoot are the payload signature certificates
* GlobalSign certificates are TLS certificates to access SMK
* Let's Encrypt certificates are TLS certificates to access Directory

The password to the trust store is `de4a`.
