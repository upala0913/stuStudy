package com.issCollege.stustudy.mapper;

import com.issCollege.stustudy.po.ExamAllView;
import com.issCollege.stustudy.po.ExamAllViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamAllViewMapper {
    long countByExample(ExamAllViewExample example);

    int deleteByExample(ExamAllViewExample example);

    int insert(ExamAllView record);

    int insertSelective(ExamAllView record);

    List<ExamAllView> selectByExample(ExamAllViewExample example);

    int updateByExampleSelective(@Param("record") ExamAllView record, @Param("example") ExamAllViewExample example);

    int updateByExample(@Param("record") ExamAllView record, @Param("example") ExamAllViewExample example);
}