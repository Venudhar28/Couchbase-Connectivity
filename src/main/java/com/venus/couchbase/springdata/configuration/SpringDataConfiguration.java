package com.venus.couchbase.springdata.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.venus.couchbase.common.CouchbaseConfiguration;

@Configuration
@EnableCouchbaseRepositories("com.venus.couchbase.springdata.dao")
public class SpringDataConfiguration extends AbstractCouchbaseConfiguration {

	/** The Constant NAME. */
	private static final String BUCKET_NAME = "name";

	/** The Constant BUCKET_PASSWORD. */
	private static final String BUCKET_PASS = "password";
	
	@Autowired
	CouchbaseConfiguration couchbaseConfiguration;
	
	@Override
	protected String getBucketName() {
		return couchbaseConfiguration.getBucket().get(BUCKET_NAME);
	}

	@Override
	protected String getBucketPassword() {
		return couchbaseConfiguration.getBucket().get(BUCKET_PASS);
	}

	@Override
	protected List<String> getBootstrapHosts() {
		return Arrays.asList(couchbaseConfiguration.getNodes());
	}
}