package com.venus.couchbase.sdk.configuration;

import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.query.core.N1qlQueryExecutor;
import com.venus.couchbase.common.CouchbaseConfiguration;

/**
 * This CouchbaseService class creates the Cluster and opens the bucket with the
 * given bucket name and password.
 */
@Component
public class SdkConfiguration {

	/** The bucket. */
	private Bucket bucket;

	/** The cluster. */
	private Cluster cluster;

	/** The bucket name. */
	private String bucketName;

	/** The Constant NAME. */
	private static final String BUCKET_NAME = "name";
	
	private static final String BUCKET_USER_NAME = "user";

	/** The Constant BUCKET_PASSWORD. */
	private static final String BUCKET_PASS = "password";
	
	/**
	 * Instantiates a new SdkConnection.
	 *
	 * @param couchbaseConfiguration
	 *            the couchbase configuration
	 */
	@Autowired
	public SdkConfiguration(CouchbaseConfiguration couchbaseConfiguration) {
		try
		{
			this.cluster = CouchbaseCluster.create(couchbaseConfiguration.getNodes());
			cluster.authenticate(couchbaseConfiguration.getBucket().get(BUCKET_USER_NAME), couchbaseConfiguration.getBucket().get(BUCKET_PASS));
			this.bucket = cluster.openBucket(couchbaseConfiguration.getBucket().get(BUCKET_NAME), 600, TimeUnit.SECONDS);
			this.bucketName = couchbaseConfiguration.getBucket().get(BUCKET_NAME);
			System.setProperty(N1qlQueryExecutor.ENCODED_PLAN_ENABLED_PROPERTY,"false");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Gets the bucket.
	 *
	 * @return the bucket
	 */
	public Bucket getBucket() {
		return bucket;
	}

	/**
	 * Gets the bucket name.
	 *
	 * @return the bucket name
	 */
	public String getBucketName() {
		return bucketName;
	}

	/**
	 * Pre destroy.
	 */
	@PreDestroy
	public void preDestroy() {
		if (this.cluster != null) {
			System.out.println("##################################### Calling @PreDestroy...");
			this.cluster.disconnect();
		}
	}
}
