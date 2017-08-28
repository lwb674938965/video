package com.zhiyou100.video.model;

import java.util.Arrays;
import java.util.Date;

public class Video {
    private Integer id;

    private String videoTitle;

    private Integer speakerId;

    private Integer courseId;

    private Integer videoLength;

    private String videoImageUrl;

    private String videoUrl;

    private String videoDescr;

    private Date insertTime;

    private Date updateTime;

    private Integer videoPlayTimes;
    
    private String sName;
    
    private String cName;
    
    private Course cr;
    
    private Speaker sk;
    
    private int currentPage;
    
    private  int[] rowCheck;
    
    

	public int[] getRowCheck() {
		return rowCheck;
	}

	public void setRowCheck(int[] rowCheck) {
		this.rowCheck = rowCheck;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public Integer getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(Integer speakerId) {
		this.speakerId = speakerId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getVideoLength() {
		return videoLength;
	}








	public void setVideoLength(Integer videoLength) {
		this.videoLength = videoLength;
	}








	public String getVideoImageUrl() {
		return videoImageUrl;
	}








	public void setVideoImageUrl(String videoImageUrl) {
		this.videoImageUrl = videoImageUrl;
	}








	public String getVideoUrl() {
		return videoUrl;
	}








	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}








	public String getVideoDescr() {
		return videoDescr;
	}








	public void setVideoDescr(String videoDescr) {
		this.videoDescr = videoDescr;
	}








	public Date getInsertTime() {
		return insertTime;
	}








	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}








	public Date getUpdateTime() {
		return updateTime;
	}








	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}








	public Integer getVideoPlayTimes() {
		return videoPlayTimes;
	}








	public void setVideoPlayTimes(Integer videoPlayTimes) {
		this.videoPlayTimes = videoPlayTimes;
	}








	public String getsName() {
		return sName;
	}








	public void setsName(String sName) {
		this.sName = sName;
	}








	public String getcName() {
		return cName;
	}








	public void setcName(String cName) {
		this.cName = cName;
	}








	public Course getCr() {
		return cr;
	}








	public void setCr(Course cr) {
		this.cr = cr;
	}








	public Speaker getSk() {
		return sk;
	}








	public void setSk(Speaker sk) {
		this.sk = sk;
	}








	public int getCurrentPage() {
		return currentPage;
	}








	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}








	@Override
	public String toString() {
		return "Video [id=" + id + ", videoTitle=" + videoTitle + ", speakerId=" + speakerId + ", courseId=" + courseId
				+ ", videoLength=" + videoLength + ", videoImageUrl=" + videoImageUrl + ", videoUrl=" + videoUrl
				+ ", videoDescr=" + videoDescr + ", insertTime=" + insertTime + ", updateTime=" + updateTime
				+ ", videoPlayTimes=" + videoPlayTimes + ", sName=" + sName + ", cName=" + cName + ", cr=" + cr
				+ ", sk=" + sk + ", currentPage=" + currentPage + ", rowCheck=" + Arrays.toString(rowCheck) + "]";
	}
    
    
    
    
    
    
    
}