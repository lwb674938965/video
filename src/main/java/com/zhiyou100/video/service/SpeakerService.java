package com.zhiyou100.video.service;

import java.util.List;

import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;

public interface SpeakerService {
	
	Page<Speaker> findAllSpeakerByConditions(String speakerName, String speakerJob, int currentPage);

	void addSpeaker(Speaker speaker);

	Speaker findSpeakerById(int id);

	void editSpeaker(Speaker speaker);

	void deleteSpeakerById(int id);

	List<Speaker> findAllSpeaker();

}
