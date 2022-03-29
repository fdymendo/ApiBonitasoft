package com.fdymendo.apibonitasoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fdymendo.apibonitasoft.model.Input;
import com.fdymendo.apibonitasoft.service.BonitasoftService;

@CrossOrigin
@RestController
@RequestMapping("/createProcess")
public class CreateProcess {

  @Autowired
  private BonitasoftService bonitasoftService;

  @PostMapping()
  public void getCreateProcess(@RequestBody @Validated Input input) {
    this.bonitasoftService.initProcess(input);
  }
}
