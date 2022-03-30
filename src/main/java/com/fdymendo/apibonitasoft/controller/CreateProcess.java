package com.fdymendo.apibonitasoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fdymendo.apibonitasoft.model.Input;
import com.fdymendo.apibonitasoft.service.BonitasoftService;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/createProcess")
public class CreateProcess {

  @Autowired
  private Gson gson;

  @Autowired
  private BonitasoftService bonitasoftService;

  @PostMapping()
  public ResponseEntity<String> getCreateProcess(@RequestBody @Validated Input input) {
    log.info("Information receive: {}", this.gson.toJson(input));
    return bonitasoftService.initProcess(input);
  }
}
