package com.zhiyou100.video.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.service.SubjectService;
import com.zhiyou100.video.service.VideoService;

@Controller
@RequestMapping("/front/video")
public class FrontVideoController {

	@Autowired
	SubjectService ss;
	@Autowired
	CourseService cr;
	@Autowired
	SpeakerService sk;
	@Autowired
	VideoService vs;
	@RequestMapping("/index.do")
	public String getVideo(Integer videoId,Integer subjectId,Model md){
		Subject subject = ss.findSubjectById(subjectId);
		md.addAttribute("subject", subject);
		md.addAttribute("videoId", videoId);
		
		return "front/video/index";
	}
	@RequestMapping("/videoData.do")
	public String loadVideo(Integer videoId,Model md){
		Video video = vs.findVideoById(videoId);
		Subject sj = new Subject();
		Speaker speaker = sk.findSpeakerById(video.getSpeakerId());
		Course course = cr.findCourseById(video.getCourseId());
		List<Video> videoList = vs.findVideosByCourseId(course.getId());
		for(Video vd:videoList){
			Course course2 = cr.findCourseById(vd.getCourseId());
			sj.setId(course2.getSubjectId());
		}
		md.addAttribute("videoList", videoList);
		md.addAttribute("subjectId", sj.getId());
		md.addAttribute("course", course);
		md.addAttribute("speaker", speaker);
		md.addAttribute("video", video);
		return "front/video/content";
	}
	@RequestMapping("/state.do")
	public void getPlayTimes(Integer videoId){
		//System.out.println(videoId);
		Video video = vs.findVideoById(videoId);
		video.setVideoPlayTimes(video.getVideoPlayTimes()+1);
		vs.editVideo(video);
		
	}
}
