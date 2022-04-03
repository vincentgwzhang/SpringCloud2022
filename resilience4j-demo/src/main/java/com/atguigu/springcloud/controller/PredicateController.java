package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.RecordPredicateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("predicate")
public class PredicateController {

    private final RecordPredicateService recordPredicateService;

    @Autowired
    public PredicateController(final RecordPredicateService recordPredicateService) {
        this.recordPredicateService = recordPredicateService;
    }

    @GetMapping
    public String checkPredicate() {
        this.recordPredicateService.testPredicateChecking();
        return "OK";
    }

}
