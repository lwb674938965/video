package com.zhiyou100.video.model;

public class Mail {
    private Integer id;

    private String email;

    private Integer status;

    private Integer activenum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getActivenum() {
        return activenum;
    }

    public void setActivenum(Integer activenum) {
        this.activenum = activenum;
    }

	@Override
	public String toString() {
		return "Mail [id=" + id + ", email=" + email + ", status=" + status + ", activenum=" + activenum + "]";
	}
    
    
}