package com.zhiyou100.video.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SubjectService;
import com.zhiyou100.video.service.VideoService;

@Controller
@RequestMapping("/front/course")
public class FrontCourseController {
	
	@Autowired
	CourseService cs;
	@Autowired
	SubjectService ss;
	@Autowired
	VideoService vs;
	
	
	@RequestMapping("/index.do")
	public String getCourse(Integer subjectId,Model md){
		Subject sb = ss.findSubjectById(subjectId);
		List<Course> list = cs.findCoursesById(subjectId);
		for(Course cr : list){
			List<Video> videoList = vs.findVideosByCourseId(cr.getId());
			//System.out.println(videoList);
			cr.setVideoList(videoList);
		}
		md.addAttribute("courses", list);
		md.addAttribute("subjectId", subjectId);
		md.addAttribute("subject", sb);
		return "front/course/index";
	}

}
