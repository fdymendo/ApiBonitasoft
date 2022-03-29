package com.fdymendo.apibonitasoft.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessDetail {
  
  private String displayDescription;
  private String deploymentDate;
  private String displayName;
  private String name;
  private String description;
  private String deployedBy;
  private String id;
  private String activationState;
  private String version;
  private String configurationState;
  private String last_update_date;
  private String actorinitiatorid;
  
}
