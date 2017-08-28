package com.zhiyou100.video.model;


public class VideoTimes {

	private String names;
	
	private int times;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "VideoTimes [names=" + names + ", times=" + times + "]";
	}

	
}
