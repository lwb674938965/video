package com.zhiyou100.video.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Subject;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SubjectService;

@Controller
@RequestMapping("/admin/course")
public class CourseController {
	@Autowired
	CourseService cs;
	@Autowired
	SubjectService sj;
	
	//课程管理
		@RequestMapping("/courseList")
		public ModelAndView courseList(HttpServletRequest req,@RequestParam(value="page",defaultValue="1",required=true)int currentPage){
			Page<Course> page = cs.findAllCourseByConditions(currentPage);
			ModelAndView mav = new ModelAndView();
			mav.addObject("page", page);
			mav.setViewName("admin/course/lessonList");
			return mav;
		}
		
		//修改课程
			@RequestMapping(value = "/editCourse.action", method = RequestMethod.GET)
			public String editCourse(int id,Model md,int page){
				List<Subject> subjectList = sj.findAllSubject();
				Course course = cs.findCourseById(id);
				md.addAttribute("course", course);
				md.addAttribute("page", page);
				md.addAttribute("editSubjectList", subjectList);
				return "admin/course/editCourse";
			}
			@RequestMapping(value = "/editCourse.action", method = RequestMethod.POST)
			public String editCourse(Course course,int page){
				cs.editCourse(course);
				return "redirect:/admin/course/courseList.action?page="+page;
			}
		
		//添加课程
			@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
			public String addCourse(Model md) {
				List<Subject> subjectList = sj.findAllSubject();
				md.addAttribute("addSubjectList", subjectList);
				return "admin/course/addLesson";
			}

			@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
			public String addCourse(Course course){
				cs.addCourse(course);
				return "redirect:/admin/course/courseList.action";
			}	
		//删除课程
			
			@RequestMapping(value = "/deleteCourse.action", method = RequestMethod.POST)
			@ResponseBody
			public String deleteCourse(int id){
				System.out.println(id);
				cs.deleteCourseById(id);	
				return "success";
			}
		

}
