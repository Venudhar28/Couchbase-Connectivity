package com.venus.couchbase.sdk.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.venus.couchbase.sdk.bean.TravelBean;
import com.venus.couchbase.sdk.dao.SdkTravelAsyncQuery;
import com.venus.couchbase.sdk.dao.SdkTravelSyncQuery;

/**
 * The Class SdkService.
 */
@Service
public class SdkService {

	/** The Constant logger. */
	protected static final Logger logger = LoggerFactory.getLogger(SdkService.class);
	
	/** The async query. */
	@Autowired
	private SdkTravelAsyncQuery asyncQuery;


	/** The sync query. */
	@Autowired
	private SdkTravelSyncQuery syncQuery;
	
	/**
	 * Find travel by city name async.
	 *
	 * @param cityname the cityname
	 * @return the list
	 */
	public List<TravelBean> findTravelByCityNameAsync(String cityname) {

		List<JsonObject> travelJsons = asyncQuery.findTravelByCityName(Arrays.asList(cityname.split("\\s*,\\s*")));

		long startTime = System.currentTimeMillis();
		List<TravelBean> travelBeans = constructTravelBeansFromJsonObjects(travelJsons);
		logger.info("Serialization Time : {}", (System.currentTimeMillis() - startTime));
		
		return travelBeans;
	}

	/**
	 * Construct travel beans from json objects.
	 *
	 * @param travelJsons the travel jsons
	 * @return the list
	 */
	private List<TravelBean> constructTravelBeansFromJsonObjects(List<JsonObject> travelJsons) {
		TravelBean travelBean = null;
		ObjectMapper mapperObj = new ObjectMapper();
		mapperObj.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		List<TravelBean> listEquipmentProductConfiguration = new ArrayList<>();
		try {
			if (!travelJsons.isEmpty()) {
				for (JsonObject jsonObect : travelJsons) {
					travelBean = mapperObj.readValue(jsonObect.toString(), TravelBean.class);
					listEquipmentProductConfiguration.add(travelBean);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return listEquipmentProductConfiguration;
	}
	
	/**
	 * Find travel by city name sync.
	 *
	 * @param cityname the cityname
	 * @return the list
	 */
	public List<TravelBean> findTravelByCityNameSync(String cityname) {

//		List<N1qlQueryRow> travelN1qlJsons = syncQuery.findTravelByCityName(Arrays.asList(cityname.split("\\s*,\\s*")));

		N1qlQueryResult result = syncQuery.findTravelByCityName(Arrays.asList(cityname.split("\\s*,\\s*")));
		
		long startTime = System.currentTimeMillis();

//		List<TravelBean> travelBeans = constructTravelBeansFromN1qlRows(travelN1qlJsons);

		List<TravelBean> travelBeans = constructTravelBeansFromN1qlQueryResult(result);
		
		logger.info("Serialization Time : {}", (System.currentTimeMillis() - startTime));
		
		return travelBeans;
	}
	
	/**
	 * Construct travel beans from N1ql rows.
	 *
	 * @param travelN1qlJsons the travel N1ql jsons
	 * @return the list
	 */
	private List<TravelBean> constructTravelBeansFromN1qlRows(List<N1qlQueryRow> travelN1qlJsons) {
		TravelBean travelBean = null;
		ObjectMapper mapperObj = new ObjectMapper();
		mapperObj.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		List<TravelBean> listEquipmentProductConfiguration = new ArrayList<>();
		try {
			if (!travelN1qlJsons.isEmpty()) {
				for (N1qlQueryRow n1qlRow : travelN1qlJsons) {
					travelBean = mapperObj.readValue(n1qlRow.value().toString(), TravelBean.class);
					listEquipmentProductConfiguration.add(travelBean);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return listEquipmentProductConfiguration;
	}
	
	private List<TravelBean> constructTravelBeansFromN1qlQueryResult(N1qlQueryResult result) {
		TravelBean travelBean = null;
		ObjectMapper mapperObj = new ObjectMapper();
		mapperObj.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		List<TravelBean> listEquipmentProductConfiguration = new ArrayList<>();
		try {
			if (!result.allRows().isEmpty() && result.errors().isEmpty()) {
				for (N1qlQueryRow n1qlRow : result.allRows()) {
					travelBean = mapperObj.readValue(n1qlRow.value().toString(), TravelBean.class);
					listEquipmentProductConfiguration.add(travelBean);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return listEquipmentProductConfiguration;
	}
}