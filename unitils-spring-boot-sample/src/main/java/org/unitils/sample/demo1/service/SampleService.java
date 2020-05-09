package org.unitils.sample.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unitils.sample.demo1.mapper.SampleMapper;
import org.unitils.sample.demo1.model.Sample;


@Service
public class SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    public Sample getValueById(int id){
        return sampleMapper.selectByPrimaryKey(id);
    }

}
