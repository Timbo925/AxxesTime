package com.axxes.timesheet.time.controller;

import com.axxes.timesheet.time.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/projects")
public class ContractController {


    @Autowired
    private ContractService contractService;


}
