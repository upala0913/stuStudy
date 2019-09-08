package com.issCollege.stustudy.po;

import java.util.Date;

public class Course {
    @Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", created=" + created + "]";
	}

	private Long id;

    private String name;

    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}