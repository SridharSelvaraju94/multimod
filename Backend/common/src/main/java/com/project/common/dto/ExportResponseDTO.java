
package com.project.common.dto;

public class ExportResponseDTO {

	private String fileDestination;

	private String fileName;

	/**
	 * @return the fileDestination
	 */
	public String getFileDestination() {
		return fileDestination;
	}

	/**
	 * @param fileDestination the fileDestination to set
	 */
	public void setFileDestination(String fileDestination) {
		this.fileDestination = fileDestination;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
