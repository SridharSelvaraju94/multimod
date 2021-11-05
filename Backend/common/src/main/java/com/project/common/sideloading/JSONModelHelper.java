package com.project.common.sideloading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.common.constants.CommonConstants;

public class JSONModelHelper {

	private JSONModelHelper() {
	}

	/**
	 * @param
	 * @param collection
	 * @return
	 */
	public static JSONModel processJSONModelForCollection(String statusCode, String statusMsg, List<?> collection,
                                                        int totalPages, Long totalRecords) {
		Map<String, Object> metaMap = new HashMap<>();
		metaMap.put(CommonConstants.STATUS_CODE, statusCode);
		metaMap.put(CommonConstants.STATUS_MSG, statusMsg);
		metaMap.put(CommonConstants.TOTAL_PAGES, totalPages);
		metaMap.put(CommonConstants.TOTAL_RECORDS, totalRecords);
		return new JSONModel.Builder<>(collection).addMeta(metaMap).build();
	}

	public static JSONModel processJSONModelForCollection(String statusCode, String statusMsg, List<?> collection) {
		Map<String, Object> metaMap = new HashMap<>();
		metaMap.put(CommonConstants.STATUS_CODE, statusCode);
		metaMap.put(CommonConstants.STATUS_MSG, statusMsg);
		return new JSONModel.Builder<>(collection).addMeta(metaMap).build();
	}

	public static JSONModel processJSONModelForObject(String statusCode, String statusMessage, Object entity) {
		Map<String, Object> metaMap = new HashMap<>();
		metaMap.put(CommonConstants.STATUS_CODE, statusCode);
		if (statusMessage != null && statusMessage.trim().length() > 0) {
			metaMap.put(CommonConstants.STATUS_MSG, statusMessage);
		}
		return new JSONModel.Builder<>(entity).addMeta(metaMap).build();
	}

	public static JSONModel processJSONModelForObject(String statusCode, String statusMessage) {
		Map<String, Object> metaMap = new HashMap<>();
		metaMap.put(CommonConstants.STATUS_CODE, statusCode);
		if (statusMessage != null && statusMessage.trim().length() > 0) {
			metaMap.put(CommonConstants.STATUS_MSG, statusMessage);
		}
		return new JSONModel.Builder<>().addMeta(metaMap).build();
	}

	public static JSONModel processJSONModelForException(String statusCode, String message) {
		Map<String, Object> metaMap = new HashMap<>();
		metaMap.put(CommonConstants.ERROR_CODE, statusCode);
		metaMap.put(CommonConstants.ERROR_MSG, message);

		return new JSONModel.Builder<>().addMeta(metaMap).build();
	}

}
