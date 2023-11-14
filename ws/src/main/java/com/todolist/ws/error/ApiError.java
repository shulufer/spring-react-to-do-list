package com.todolist.ws.error;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiError {

    private int status;

    private String message;

    private String path;

    private long timestap = new Date().getTime();

    private Map<String, String> validationErrors = new HashMap<>();


  public int getStatus() {
    return this.status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public long getTimestap() {
    return this.timestap;
  }

  public void setTimestap(long timestap) {
    this.timestap = timestap;
  }


  public Map<String,String> getValidationErrors() {
    return this.validationErrors;
  }

  public void setValidationErrors(Map<String,String> validationErrors) {
    this.validationErrors = validationErrors;
  }


}
