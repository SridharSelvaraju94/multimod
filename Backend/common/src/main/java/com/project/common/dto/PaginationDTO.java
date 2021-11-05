
package com.project.common.dto;

import java.util.List;

public class PaginationDTO<T> {
  
  private int totalPages;
  private long totalRecord;
  private List<T> data;
  
  public PaginationDTO() {}
  
  public PaginationDTO (int pages, long record, List<T> data){
    this.totalPages = pages;
    this.totalRecord = record;
    this.data = data;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalRecord() {
    return totalRecord;
  }

  public void setTotalRecord(long totalRecord) {
    this.totalRecord = totalRecord;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }



}
