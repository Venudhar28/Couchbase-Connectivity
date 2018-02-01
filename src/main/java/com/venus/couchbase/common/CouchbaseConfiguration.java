package com.venus.couchbase.common;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "couchbase")
public class CouchbaseConfiguration {

	/** The nodes. */
	private String nodes;

	/** The bucket. */
	private Map<String, String> bucket;
	
	/**
	 * Gets the nodes.
	 *
	 * @return the nodes
	 */
	public String getNodes() {
		return nodes;
	}

	/**
	 * Sets the nodes.
	 *
	 * @param nodes
	 *            the nodes to set
	 */
	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	/**
	 * Gets the bucket.
	 *
	 * @return the bucket
	 */
	public Map<String, String> getBucket() {
		return bucket;
	}

	/**
	 * Sets the bucket.
	 *
	 * @param bucket
	 *            the bucket to set
	 */
	public void setBucket(Map<String, String> bucket) {
		this.bucket = bucket;
	}
}
