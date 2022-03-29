package com.fdymendo.apibonitasoft.service.implement;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fdymendo.apibonitasoft.config.ApplicationConfig;
import com.fdymendo.apibonitasoft.model.Input;
import com.fdymendo.apibonitasoft.model.Token;
import com.fdymendo.apibonitasoft.service.BonitasoftService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BonitaSoftServiceImpl implements BonitasoftService {

  @Autowired
  private ApplicationConfig applicationConfig;

  @Override
  public void initProcess(Input input) {
    Token token = getToken();
    String id = getIdProcess(token);
    createProcess(token, id, input);
  }

  @Override
  public void createProcess(Token token, String id, Input input) {



    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(applicationConfig.getHeaderToken(), token.getToken());
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("Host", "localhost:8080");
    httpHeaders.add("Cookie", token.getCookie());

    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Input> request = new HttpEntity<>(input, httpHeaders);
    try {
      String url = this.applicationConfig.getBonitaUrlCreateProcessPart1() + id
          + this.applicationConfig.getBonitaUrlCreateProcessPart2();
      ResponseEntity<Object> response =
          restTemplate.exchange(url, HttpMethod.POST, request, Object.class);
      log.info(response);
    } catch (Exception e) {
      log.error("error", e);
    }


  }

  @Override
  public Token getToken() {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("PRIVATE-TOKEN", "xyz");

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

    map.add("username", this.applicationConfig.getBonitaUsername());
    map.add("password", this.applicationConfig.getBonitaPassword());
    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
    RestTemplate restTemplate = new RestTemplate();

    Token token = new Token();

    try {
      ResponseEntity<Object> response = restTemplate.exchange(
          this.applicationConfig.getBonitaUrlLogin(), HttpMethod.POST, entity, Object.class);
      HttpHeaders headersresponse = response.getHeaders();
      List<String> setCookie = headersresponse.get(HttpHeaders.SET_COOKIE);
      String headerTenant = "";
      String headerBos = "";
      String headerJsessionid = "";
      String headerToken = "";
      for (String tmp : setCookie) {
        log.info(tmp);
        if (tmp.contains(this.applicationConfig.getHeaderTenant())) {
          headerTenant = getHeader(tmp, this.applicationConfig.getHeaderTenant());
        } else if (tmp.contains(this.applicationConfig.getHeaderBos())) {
          headerBos = getHeader(tmp, this.applicationConfig.getHeaderBos());
        } else if (tmp.contains(this.applicationConfig.getHeaderJsessionid())) {
          headerJsessionid = getHeader(tmp, this.applicationConfig.getHeaderJsessionid());
        } else if (tmp.contains(this.applicationConfig.getHeaderToken())) {
          headerToken = getHeader(tmp, this.applicationConfig.getHeaderToken());
        }
      }
      token.createCookie(headerTenant, headerBos, headerJsessionid, headerToken);
      token.setToken(getHeaderToken(headerToken));
      log.info(headersresponse);

    } catch (Exception e) {
      log.error("error", e);
    }
    return token;
  }

  private String getHeader(String textHeader, String header) {
    String[] tmpList = textHeader.split(";");
    for (String tmp : tmpList) {
      if (tmp.contains(header)) {
        return tmp;
      }
    }
    return "";
  }

  private String getHeaderToken(String header) {
    String[] tmp = header.split("=");
    return tmp[1];
  }

  @Override
  public String getIdProcess(Token token) {

    String processId = "";
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X-Bonita-API-Token", token.getToken());
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("Host", "localhost:8080");
    httpHeaders.add("Cookie", token.getCookie());

    RestTemplate restTemplate = new RestTemplate();


    HttpEntity<Void> request = new HttpEntity<>(httpHeaders);
    try {
      String urlService = this.applicationConfig.getBonitaUrlSearchProcess()
          .concat(this.applicationConfig.getProcessName());
      @SuppressWarnings("rawtypes")
      ResponseEntity<List> response =
          restTemplate.exchange(urlService, HttpMethod.GET, request, List.class);
      @SuppressWarnings("unchecked")
      List<LinkedHashMap<String, String>> processDetails = response.getBody();
      LinkedHashMap<String, String> processDetail = processDetails.get(0);

      processId = processDetail.get("id");
    } catch (Exception e) {
      log.error("error", e);
    }
    return processId;
  }

}
