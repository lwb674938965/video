package com.zhiyou100.video.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.SpeakerMapper;
import com.zhiyou100.video.model.Packing;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.SpeakerService;
@Service
public class SpeakerServiceImpl implements SpeakerService {
	@Autowired
	SpeakerMapper sm;
	
	
	@Override
	public Page<Speaker> findAllSpeakerByConditions(String speakerName, String speakerJob, int currentPage) {
		Page<Speaker> page = new Page<>();
		page.setSize(5);
		page.setPage(currentPage);
		Speaker sp = new Speaker();
		sp.setSpeakerName(speakerName);
		sp.setSpeakerJob(speakerJob);
		Packing pack = new Packing();
		pack.setCurrentPage((currentPage-1)*5);
		pack.setSpeaker(sp);
		page.setTotal(sm.findAllSpeakerCount(pack));
		page.setRows(sm.findAllSpeakerByConditions(pack));
		
		//System.out.println(sm.findAllSpeakerCount(pack));
		//System.out.println(sm.findAllSpeakerByConditions(pack));
		return page;
	}

	@Override
	public void addSpeaker(Speaker speaker) {
		speaker.setInsertTime(new Timestamp(System.currentTimeMillis()));
		sm.insertSelective(speaker);
		
	}

	@Override
	public Speaker findSpeakerById(int id) {
		
		return sm.selectByPrimaryKey(id);
	}

	@Override
	public void editSpeaker(Speaker speaker) {
		speaker.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		sm.updateByPrimaryKeySelective(speaker);
		
	}

	@Override
	public void deleteSpeakerById(int id) {
		sm.deleteByPrimaryKey(id);
		
	}

	@Override
	public List<Speaker> findAllSpeaker() {
		
		return sm.selectByExample(null);
	}

}
