package com.venus.couchbase.springdata.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.venus.couchbase.sdk.bean.TravelBean;
import com.venus.couchbase.sdk.controller.SdkRestController;
import com.venus.couchbase.springdata.document.TravelDocument;
import com.venus.couchbase.springdata.service.SpringDataService;

/**
 * The Class SpringDataRestController.
 */
@RequestMapping("/couchbase/springdata")
@Produces({ MediaType.APPLICATION_JSON })
@Controller
public class SpringDataRestController {

	/** The Constant logger. */
	protected static final Logger logger = LoggerFactory.getLogger(SpringDataRestController.class);
	
	/** The spring data service. */
	@Autowired
	private SpringDataService springDataService;
	
	/**
	 * Find travel by city name.
	 *
	 * @param cityname the cityname
	 * @return the response entity
	 */
	@GET
	@RequestMapping(value = "/city", method = RequestMethod.GET)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<List<TravelDocument>> findTravelByCityName(@QueryParam("cityname") String cityname) {
		
		long startTime = System.currentTimeMillis();

		List<TravelDocument> data = springDataService.findTravelByCityName(cityname);
		
		ResponseEntity<List<TravelDocument>>  response =   new ResponseEntity<List<TravelDocument>>(data, HttpStatus.OK);
		
		logger.info("API Response Time : {}", (System.currentTimeMillis() - startTime));
		return response;
	}
}
