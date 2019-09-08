package com.issCollege.stustudy.po;

import java.util.Date;

import com.issCollege.util.AgeConvertBirth;

public class Stuinfo {
    private Long id;
    private Integer age;

    private String name;

	private String sex;

	private Date created;

    private Boolean married;

    private String realname;

    private String password;

    private Integer state;

    private Date birth;

    private String likes;

    private String pic;

    private Long classes;

    private String info;

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stuinfo other = (Stuinfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (realname == null) {
			if (other.realname != null)
				return false;
		} else if (!realname.equals(other.realname))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}

    public Integer getAge() {
		return age;
	}

    public Date getBirth() {
        return birth;
    }

    public Long getClasses() {
        return classes;
    }

    public Date getCreated() {
        return created;
    }

    public Long getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public String getLikes() {
        return likes;
    }

    public Boolean getMarried() {
        return married;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPic() {
        return pic;
    }

    public String getRealname() {
        return realname;
    }

    public String getSex() {
        return sex;
    }

    public Integer getState() {
        return state;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((realname == null) ? 0 : realname.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}

    public void setAge(Integer age) {
		this.age = age;
	}

    public void setBirth(Date birth) {
        this.birth = birth;
        if(this.birth!=null) {
        	this.age=AgeConvertBirth.birthToAge(birth);
        }
    }

    public void setClasses(Long classes) {
        this.classes = classes;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public void setLikes(String likes) {
        this.likes = likes == null ? null : likes.trim();
    }

	public void setMarried(Boolean married) {
        this.married = married;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
	public String toString() {
		return "Stuinfo [id=" + id + ", age=" + age + ", name=" + name + ", sex=" + sex + ", created=" + created
				+ ", married=" + married + ", realname=" + realname + ", password=" + password + ", state=" + state
				+ ", birth=" + birth + ", likes=" + likes + ", pic=" + pic + ", classes=" + classes + ", info=" + info
				+ "]";
	}
}