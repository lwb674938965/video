package com.zhiyou100.video.model;

public class Packing {
	
	private Video video;
	
	private int currentPage;
	
	private Speaker speaker;
	
	private Course course;
 
    

	
	
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "Packing [video=" + video + ", currentPage=" + currentPage + ", speaker=" + speaker + ", course="
				+ course + "]";
	}
	
	

}
