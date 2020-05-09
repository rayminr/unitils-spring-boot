package org.unitils.sample.demo2.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.unitils.sample.demo2.model.Msg;
import org.unitils.sample.demo2.model.MsgExample;

public interface MsgMapper {
    long countByExample(MsgExample example);

    int deleteByPrimaryKey(Integer code);

    int insert(Msg record);

    int insertSelective(Msg record);

    List<Msg> selectByExample(MsgExample example);

    Msg selectByPrimaryKey(Integer code);

    int updateByExampleSelective(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByExample(@Param("record") Msg record, @Param("example") MsgExample example);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);
}