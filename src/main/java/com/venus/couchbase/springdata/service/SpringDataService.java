package com.venus.couchbase.springdata.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venus.couchbase.sdk.service.SdkService;
import com.venus.couchbase.springdata.dao.SpringDataRepository;
import com.venus.couchbase.springdata.document.TravelDocument;

/**
 * The Class SpringDataService.
 */
@Service
public class SpringDataService {
	
	/** The Constant logger. */
	protected static final Logger logger = LoggerFactory.getLogger(SdkService.class);
	
	/** The travel repo. */
	@Autowired
	private SpringDataRepository travelRepo;
	
	/**
	 * Find travel by city name.
	 *
	 * @param cityname the cityname
	 * @return the list
	 */
	public List<TravelDocument> findTravelByCityName(String cityname) {

		long startTime = System.currentTimeMillis();

		List<TravelDocument> travelDocs = travelRepo.findCitiesByCityname(cityname);
		
		logger.info("Query Execution Time : {}", (System.currentTimeMillis() - startTime));

		return travelDocs;
	}
}