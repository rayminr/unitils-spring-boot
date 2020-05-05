package org.unitils.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unitils.sample.mapper.SampleMapper;
import org.unitils.sample.model.Sample;

@Service
public class SampleService {

    @Autowired
    private SampleMapper sampleMapper ;

    public Sample getValueById(int id){
        return sampleMapper.selectByPrimaryKey(id);
    }
}
