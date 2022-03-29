package com.fdymendo.apibonitasoft.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token {

  private String cookie;
  private String token;

  public void createCookie(String headerTenant, String headerBos, String headerJsessionid,
      String headerToken) {
    String separator = "; ";
    this.cookie = headerTenant.concat(separator).concat(headerBos).concat(separator)
        .concat(headerJsessionid).concat(separator).concat(headerToken);
  }
}
