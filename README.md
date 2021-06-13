# Troubleshooting Tests

## Can not connect to Ryuk at localhost:<ephemeral port>

This is caused by an incompatibility between testcontainers and your docker engine. Update to the latest version
available. The following combination works on Fedora: testcontainers 1.15.3 with docker 20.10.3.
