package com.zhiyou100.video.service;


import java.util.List;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoTimes;

public interface VideoService {

	Admin login(String username, String pwd);

	Page<Video> findAllVideoByConditions(String video_title, int speaker_id, int course_id, int currentPage);

	void addVideo(Video video);

	Video findVideoById(int id);

	void editVideo(Video video);

	void deleteVideo(int id);

	void deleteVideoByIds(int[] rowCheck);

	void deleteVideoByIds(List<Integer> list);

	List<VideoTimes> getAverageVideoNames();

	String findAdminByName(String loginName);

	void addAdmin(Admin ad);

	List<Video> findVideosByCourseId(Integer id);


	
	
	
	
	
	
	
	
	


}
