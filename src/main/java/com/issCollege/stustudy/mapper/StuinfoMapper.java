package com.issCollege.stustudy.mapper;

import com.issCollege.stustudy.po.Stuinfo;
import com.issCollege.stustudy.po.StuinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuinfoMapper {
    long countByExample(StuinfoExample example);

    int deleteByExample(StuinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Stuinfo record);

    int insertSelective(Stuinfo record);

    List<Stuinfo> selectByExampleWithBLOBs(StuinfoExample example);

    List<Stuinfo> selectByExample(StuinfoExample example);

    Stuinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Stuinfo record, @Param("example") StuinfoExample example);

    int updateByExampleWithBLOBs(@Param("record") Stuinfo record, @Param("example") StuinfoExample example);

    int updateByExample(@Param("record") Stuinfo record, @Param("example") StuinfoExample example);

    int updateByPrimaryKeySelective(Stuinfo record);

    int updateByPrimaryKeyWithBLOBs(Stuinfo record);

    int updateByPrimaryKey(Stuinfo record);
}