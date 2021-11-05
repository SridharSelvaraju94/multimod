/*******************************************************************************
 * Copyright (C) Prodapt Solution - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Prodapt,   @  22 June 2018
 * Modified  @  16 Nov 2018 
 *  
 * Description of Class - DTO for level
 ******************************************************************************/
package com.project.repo.dto;

public class LevelDTO {

	/** Level ID */
	String levelId;

	/** Level Name */
	String levelName;

	/**
	 * @return the levelId
	 */
	public String getLevelId() {
		return levelId;
	}

	/**
	 * @param levelId
	 *            the levelId to set
	 */
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * @param levelName
	 *            the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
