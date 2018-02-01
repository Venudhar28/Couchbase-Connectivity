# Couchbase-Connectivity
# This project is about connecting Couchbase with "Couchbase SDK and "Spring Data Cocuhbase". 
# A simple query on "Travel-Sample" bucket which comes out of the box with Cocuhbase is executed both synchronously and asynchronously.
# A sample rest service implemented for these query executions. End points are:
# Cocuhbase SDK Async - http://localhost:8080/couchbase/sdk/city?cityname=San Francisco&async=true
# Cocuhbase SDK Sync - http://localhost:8080/couchbase/sdk/city?cityname=San Francisco&async=false
# Spring Data Couchbase - http://localhost:8080/couchbase/springdata/city?cityname=San Francisco