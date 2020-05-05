package org.unitils.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unitils.sample.model.Sample;
import org.unitils.sample.service.SampleService;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("get-value")
    public String getValue(@RequestParam("id") Integer id) {
        Sample sample = sampleService.getValueById(id) ;
        return sample.getValue();
    }

}
