package com.venus.couchbase.springdata.dao;

import java.util.List;

import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.venus.couchbase.springdata.document.TravelDocument;

/**
 * The Interface TravelRepository.
 */
@Repository
public interface SpringDataRepository extends CrudRepository<TravelDocument, String> {
    
	/**
	 * Find cities by cityname.
	 *
	 * @param cityname the cityname
	 * @return the list
	 */
	@Query("SELECT "
			+ "META().id as _ID, "
			+ "META().cas as _CAS, "
			+ "travel.* "
			+ "FROM `travel-sample` as travel "
			+ "WHERE travel.city in [$cityname ] ")

	List<TravelDocument> findCitiesByCityname(@Param("cityname") String cityname);
}