package com.zhiyou100.video.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.AdminMapper;
import com.zhiyou100.video.mapper.VideoMapper;
import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoExample;
import com.zhiyou100.video.model.VideoTimes;
import com.zhiyou100.video.service.VideoService;
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	AdminMapper am;
	@Autowired
	VideoMapper vm;

	public Admin login(String username, String pwd) {
		Admin ad = am.selectByNameAndPwd(username,pwd);
		//System.out.println(ad);
		return ad;
	}

	@Override
	public Page<Video> findAllVideoByConditions(String video_title, int speaker_id, int course_id, int currentPage) {
		Page<Video> page = new Page<>();
		page.setSize(5);
		page.setPage(currentPage);
		Video vd = new Video();
		Speaker sp = new Speaker();
		Course cs = new Course();
		sp.setId(speaker_id);
		cs.setId(course_id);
		//System.out.println(speaker_id+"--------"+course_id+"----------"+video_title);
		vd.setSk(sp);
		vd.setCr(cs);
		vd.setVideoTitle(video_title);
		vd.setCurrentPage((currentPage-1)*5);
		
		page.setTotal(vm.findAllVideoCount(vd));
		page.setRows(vm.findAllVideoByConditions(vd));
		
		//System.out.println(vm.findAllVideoCount(vd));
		//System.out.println(vm.findAllVideoByConditions(vd));
		return page;
	}

	@Override
	public void addVideo(Video video) {
		video.setInsertTime(new Timestamp(System.currentTimeMillis()));
		vm.insertSelective(video);
		
	}

	@Override
	public Video findVideoById(int id) {
		
		return vm.selectByPrimaryKey(id);
	}

	@Override
	public void editVideo(Video video) {
		vm.updateByPrimaryKeySelective(video);
		
	}

	@Override
	public void deleteVideo(int id) {
		vm.deleteByPrimaryKey(id);
		
	}

	@Override
	public void deleteVideoByIds(int[] rowCheck) {
		vm.deleteVideoByIds(rowCheck);
		
	}

	@Override
	public void deleteVideoByIds(List<Integer> list) {
		VideoExample example = new VideoExample();
		example.createCriteria().andIdIn(list);
		vm.deleteByExample(example);
		
	}

	@Override
	public List<VideoTimes> getAverageVideoNames() {
		VideoTimes vt = new VideoTimes();		
		return vm.getAverageVideoNames(vt);
	}

	@Override
	public String findAdminByName(String loginName) {
		Admin ad = am.findAdByName(loginName);
		if(ad==null){
			
			return "success";
		}
		return "fail";
	}

	@Override
	public void addAdmin(Admin ad) {
		am.insert(ad);
		
	}

	/*@Override
	public int[] getAvgVideo() {
		int a1 = vm.findAverQianDuan();
		int a2 = vm.findAverReact();
		int a3 = vm.findAverUI();
		int a4 = vm.findAverPython();
		int a5 = vm.findAverJava();		
		int [] arr= {a1,a2,a3,a4,a5};
		//System.out.println(Arrays.toString(arr));
		return arr;
	}*/



	
  
}
