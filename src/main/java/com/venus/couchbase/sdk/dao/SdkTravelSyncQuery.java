package com.venus.couchbase.sdk.dao;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.venus.couchbase.sdk.configuration.SdkConfiguration;

/**
 * The Class SdkTravelQuery.
 */
@Component
public class SdkTravelSyncQuery {

	/** The Constant logger. */
	protected static final Logger logger = LoggerFactory.getLogger(SdkTravelSyncQuery.class);
	
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
	public N1qlQueryResult findTravelByCityName(List<String> citynames) {

		long startTime = System.currentTimeMillis();
		
		JsonObject queryParameterValues = retrieveQueryParamValues(citynames);

		List<N1qlQueryRow> configN1qlQueryRows = new ArrayList<>();
		
		N1qlQueryResult result = null;
		try 
		{
			String statement = MessageFormat.format(getTravelQuery(), " `" + sdkConnection.getBucketName() + "` ");
			N1qlQuery query = N1qlQuery.parameterized(statement,  queryParameterValues);
			Bucket bucket = sdkConnection.getBucket();

			result = bucket.query(query);
			
//			for(N1qlQueryRow row : result.allRows()) {
//				configN1qlQueryRows.add(row);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}	

		logger.info("Query  Execution Time: {}", (System.currentTimeMillis() - startTime));
		
		return result;
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
