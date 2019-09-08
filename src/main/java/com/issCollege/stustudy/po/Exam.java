package com.issCollege.stustudy.po;

import java.util.Date;

public class Exam {
    private Long id;

    private Long stu;

    private Long course;

    private Integer grade;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStu() {
        return stu;
    }

    public void setStu(Long stu) {
        this.stu = stu;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}