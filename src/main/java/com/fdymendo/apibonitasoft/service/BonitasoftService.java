package com.fdymendo.apibonitasoft.service;

import com.fdymendo.apibonitasoft.model.Input;
import com.fdymendo.apibonitasoft.model.Token;

public interface BonitasoftService {

  public Token getToken();

  public String getIdProcess(Token token);

  public void createProcess(Token token, String id, Input input);

  public void initProcess(Input input);

}
