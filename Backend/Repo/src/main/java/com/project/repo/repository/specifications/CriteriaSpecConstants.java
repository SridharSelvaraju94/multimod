
package com.project.repo.repository.specifications;

import java.util.*;

/**
 * @author nikhil.v
 *
 */
public class CriteriaSpecConstants {

	public static final String ROLE = "ssmRole";
	public static final String SEARCHPARAMS = "searchParams=";
	public static final String FILTER_REGEX = "^filter\\[(.*?)\\]";
	public static final String FILTER_ALL = "filter[all]";
	public static final String FILTER_NAME = "filter[roleName]";

	public static final Map<String, List<String>> SEARCH_PARAMS;

	static {
		Map<String, List<String>> searchparams = new HashMap<>();

		List<String> roleSearchParams = Arrays.asList(FILTER_NAME);
		searchparams.put(ROLE, roleSearchParams);
		SEARCH_PARAMS = Collections.unmodifiableMap(searchparams);
	}

}
