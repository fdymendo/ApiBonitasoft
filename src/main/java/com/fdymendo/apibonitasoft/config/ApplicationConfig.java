package com.fdymendo.apibonitasoft.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Configuration
@NoArgsConstructor
public class ApplicationConfig {

  @Value("${application.header.token}")
  private String headerToken;
  @Value("${bonita.username}")
  private String bonitaUsername;
  @Value("${bonita.password}")
  private String bonitaPassword;
  @Value("${bonita.url.login}")
  private String bonitaUrlLogin;
  @Value("${application.header.tenant}")
  private String headerTenant;
  @Value("${application.header.bos}")
  private String headerBos;
  @Value("${application.header.jsessionid}")
  private String headerJsessionid;
  @Value("${bonita.processName}")
  private String processName;
  @Value("${bonita.url.searchProcess}")
  private String bonitaUrlSearchProcess;
  @Value("${bonita.url.createProcess.part1}")
  private String bonitaUrlCreateProcessPart1;
  @Value("${bonita.url.createProcess.part2}")
  private String bonitaUrlCreateProcessPart2;
}
