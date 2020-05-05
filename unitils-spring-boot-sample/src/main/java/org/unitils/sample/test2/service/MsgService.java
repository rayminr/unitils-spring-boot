package org.unitils.sample.test2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unitils.sample.test2.mapper.MsgMapper;
import org.unitils.sample.test2.model.Msg;

@Service
public class MsgService {

    @Autowired
    private MsgMapper msgMapper;

    public Msg getMsgById(int code){
        return msgMapper.selectByPrimaryKey(code);
    }
}
