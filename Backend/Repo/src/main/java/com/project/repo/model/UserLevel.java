
package com.project.repo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "level")
public class UserLevel {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "level_id")
	String levelId;

	@Column(name = "level_name")
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
