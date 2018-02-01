package com.venus.couchbase.sdk.dao;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.AsyncN1qlQueryRow;
import com.couchbase.client.java.query.N1qlParams;
import com.couchbase.client.java.query.N1qlQuery;
import com.venus.couchbase.sdk.configuration.SdkConfiguration;

/**
 * The Class SdkTravelQuery.
 */
@Component
public class SdkTravelAsyncQuery {

	/** The Constant logger. */
	protected static final Logger logger = LoggerFactory.getLogger(SdkTravelAsyncQuery.class);
	
	/** The Constant Product IDS. */
	private static final String CITYNAMES = "citynames";

	/** The sdk connection. */
	@Autowired
	private SdkConfiguration sdkConnection;

	/**
	 * Find travel by city name.
	 *
	 * @param citynames the citynames
	 * @return the list
	 */
	public List<JsonObject> findTravelByCityName(List<String> citynames) {

		long startTime = System.currentTimeMillis();
		
		N1qlParams queryParams = N1qlParams.build().adhoc(false);
		
		JsonObject queryParameterValues = retrieveQueryParamValues(citynames);

		N1qlQuery travelQuery = N1qlQuery.parameterized(
				MessageFormat.format(getTravelQuery(), " `" + sdkConnection.getBucketName() + "` "),
				queryParameterValues, queryParams);

		Bucket bucket = sdkConnection.getBucket();
		
		List<JsonObject> jsonObjects =  bucket.async().query(travelQuery).flatMap(result -> {
			return result.errors()
					.flatMap(cbex -> rx.Observable.<AsyncN1qlQueryRow>error(new CouchbaseException(
							"Error while querying Database for Travel Records :" + cbex
							+" : Couchbase configQuery =[" + travelQuery +"]")))
					.switchIfEmpty(result.rows());
		})
				.map(AsyncN1qlQueryRow::value)
				.timeout(2, TimeUnit.MINUTES)
				.toList()
				.toBlocking()
				.single();
		
		logger.info("Query  Execution Time: {}", (System.currentTimeMillis() - startTime));
		
		return jsonObjects;
	}

	/**
	 * Retrieve query param values.
	 *
	 * @param citynames the citynames
	 * @return the json object
	 */
	private JsonObject retrieveQueryParamValues(List<String> citynames) {

		List<String> listCitynames = new ArrayList();

		if (Optional.ofNullable(citynames).isPresent()) {
			listCitynames = citynames;
		}
		return JsonObject.create().put(CITYNAMES, JsonArray.from(listCitynames));
	}

	/**
	 * Gets the travel query.
	 *
	 * @return the travel query
	 */
	private String getTravelQuery() {
		return "Select" + " travel.callsign, " + " travel.country, " + " travel.iata, " + " travel.icao, "
				+ " travel.id, " + " travel.name, " + " travel.city, " + " travel.type from {0} AS travel" + " where"
				+ " travel.city in $citynames ";
	}
}
