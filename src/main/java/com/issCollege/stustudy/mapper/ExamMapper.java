package com.issCollege.stustudy.mapper;

import com.issCollege.stustudy.po.Exam;
import com.issCollege.stustudy.po.ExamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamMapper {
    long countByExample(ExamExample example);

    int deleteByExample(ExamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    int insertSelective(Exam record);

    List<Exam> selectByExample(ExamExample example);

    Exam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Exam record, @Param("example") ExamExample example);

    int updateByExample(@Param("record") Exam record, @Param("example") ExamExample example);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);
}