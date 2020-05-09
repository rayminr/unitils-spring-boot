package org.unitils.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unitils.sample.demo1.model.Sample;
import org.unitils.sample.demo1.service.SampleService;
import org.unitils.sample.demo2.model.Msg;
import org.unitils.sample.demo2.service.MsgService;
import org.unitils.spring.boot.autoconfigure.DemoService;

@RestController
@RequestMapping("/")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private DemoService demoService;

    @GetMapping("get-db-value")
    public String getDbValue(@RequestParam("id") Integer id) {
        Sample sample = sampleService.getValueById(id) ;
        return (sample == null) ? "there is no data in database!" : sample.getValue();
    }

    @GetMapping("get-db-msg")
    public String getDbMsg(@RequestParam("code") Integer code) {
        Msg msg = msgService.getMsgById(code);
        return  (msg == null) ? "there is no data in database!" : msg.getMsg();
    }

    @GetMapping("get-msg")
    public String getProMsg() {
        String msg = demoService.getMsg();
        return msg;
    }

}
