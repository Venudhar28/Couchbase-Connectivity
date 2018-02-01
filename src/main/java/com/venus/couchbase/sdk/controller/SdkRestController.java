package com.venus.couchbase.sdk.controller;

import java.util.List;import java.util.Optional;

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
import com.venus.couchbase.sdk.service.SdkService;

/**
 * The Class SdkRestController.
 */
@RequestMapping("/couchbase/sdk")
@Produces({ MediaType.APPLICATION_JSON })
@Controller
public class SdkRestController {

	/** The Constant logger. */
	protected static final Logger logger = LoggerFactory.getLogger(SdkRestController.class);
	
	/** The sdk service. */
	@Autowired
	private SdkService sdkService;
	
	/**
	 * Find travel by city name.
	 *
	 * @param cityname the cityname
	 * @return the response entity
	 */
	@GET
	@RequestMapping(value = "/city", method = RequestMethod.GET)
	@Produces({ MediaType.APPLICATION_JSON })
	public ResponseEntity<List<TravelBean>> findTravelByCityName(
			@QueryParam("cityname") String cityname, 
			@QueryParam("async") boolean async) {

		List<TravelBean> data = null;
				
		long startTime = System.currentTimeMillis();
		
		if(async) {
			data = sdkService.findTravelByCityNameAsync(cityname);
		} else {
			data = sdkService.findTravelByCityNameSync(cityname);
		}
		
		ResponseEntity<List<TravelBean>>  response =  new ResponseEntity<List<TravelBean>>(data, HttpStatus.OK);
		
		logger.info("API Response Time : {}", (System.currentTimeMillis() - startTime));
		return response;
	}
}
