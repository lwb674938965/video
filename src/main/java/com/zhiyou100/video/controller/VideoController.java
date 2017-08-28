package com.zhiyou100.video.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Admin;
import com.zhiyou100.video.model.Course;
import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoTimes;
import com.zhiyou100.video.service.CourseService;
import com.zhiyou100.video.service.SpeakerService;
import com.zhiyou100.video.service.VideoService;

@Controller
@RequestMapping("/video")
public class VideoController {

	@Autowired
	VideoService vs;
	@Autowired
	SpeakerService ss;
	@Autowired
    CourseService cs;
	//登录
	
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String login() {
		return "video/index";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password,HttpSession session) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		String pwd = new BigInteger(1, md.digest()).toString(16);
		Admin ad = vs.login(username,pwd);
		if(ad!=null){
			
			//System.out.println(username+"----"+pwd);
			session.setAttribute("adminName", ad);
			return "video/admin";
		}
		return "redirect:/video/login.action";
	}
    //视频列表
	@RequestMapping("/videoManage.action")
	public ModelAndView videoManage(@RequestParam(defaultValue="",required=true)String video_title,
			@RequestParam(defaultValue="0")int speaker_id,
			@RequestParam(defaultValue="0")int course_id,
			@RequestParam(value="page",required=true,defaultValue="1")int currentPage,HttpServletRequest req			
			) {
        
		req.setAttribute("video_title", video_title);
		req.setAttribute("speaker_id", speaker_id);
		req.setAttribute("course_id", course_id);
		List<Speaker> speakerList = ss.findAllSpeaker();
		List<Course>  courseList = cs.findAllCourse();
		
		Page<Video> page = vs.findAllVideoByConditions(video_title,speaker_id,course_id,currentPage);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.addObject("speakerList", speakerList);
		mav.addObject("courseList", courseList);
		mav.setViewName("video/videoManage");
					
		return mav;
	}
	//添加视频
	@RequestMapping(value = "/addVideo", method = RequestMethod.GET)
	public String addVideo(Model md) {
		List<Speaker> speakerList = ss.findAllSpeaker();
		List<Course>  courseList = cs.findAllCourse();
		md.addAttribute("addspeakerList", speakerList);
		md.addAttribute("addcourseList", courseList);
		
		return "video/addVideo";
	}

	@RequestMapping(value = "/addVideo", method = RequestMethod.POST)
	public String addVideo(Video video){
		vs.addVideo(video);
	
		return "forward:/video/videoManage.action";
	}
	
	//修改视频
	@RequestMapping(value = "/editVideo", method = RequestMethod.GET)
	public String editVideo(int id,Model md) {
		Video video = vs.findVideoById(id);
		List<Speaker> speakerList = ss.findAllSpeaker();
		List<Course>  courseList = cs.findAllCourse();
		md.addAttribute("editspeakerList", speakerList);
		md.addAttribute("editcourseList", courseList);
		md.addAttribute("video", video);
		return "video/editVideo";
	}

	@RequestMapping(value = "/editVideo", method = RequestMethod.POST)
	public String editVideo(Video video){
		vs.editVideo(video);
	
		return "forward:/video/videoManage.action";
	}
	
	//删除视频
	@RequestMapping(value = "/deleteVideo", method = RequestMethod.POST)
	@ResponseBody
	public String deleteVideo(int id) {
		System.out.println(id);
		vs.deleteVideo(id);
		return "success";
	}
	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	public String deleteAllVideo(int[] rowCheck) {
		System.out.println(Arrays.toString(rowCheck));
		List<Integer> list = new ArrayList<>();
		for(int i=0;i<rowCheck.length;i++){
			list.add(rowCheck[i]);
		}
		vs.deleteVideoByIds(list);
		//vs.deleteVideoByIds(rowCheck);
		return "forward:/video/videoManage.action";
	}
	@RequestMapping(value = "/videoAnalysis")
	public String videoAnalysis(Model md) {
		List<Integer> arr = new ArrayList<>();
		List<String> str = new ArrayList<>();
		List<VideoTimes>  times = vs.getAverageVideoNames();
        for(VideoTimes aa:times){
        	arr.add(aa.getTimes());
        	str.add(aa.getNames());       
        }
        arr.remove(0);
        str.remove(0);
        
        System.out.println(arr);
        System.out.println(str);
        
		md.addAttribute("arr", arr);
		md.addAttribute("str", str);
		return "video/analysis";
	}
/*	@RequestMapping(value = "/videoAnalysis")
	public String videoAnalysis(Model md) {
		int[] arr = vs.getAvgVideo();
		//System.out.println(Arrays.toString(arr));
		md.addAttribute("arr", arr);
		return "video/analysis";
	}
*/	
	
		
	//退出
	@RequestMapping(value = "/exitController")
	public String exitController(HttpSession session) {
		session.invalidate();
		return "forward:/video/login.action";
	}
	
	
	//注册
	
	@RequestMapping(value = "/regist")

	public String regist() {
		
		return "video/regist";
	}
	@RequestMapping(value = "/regist",method=RequestMethod.POST)
	@ResponseBody
	public String regist(String loginName) {
		System.out.println(loginName);
		String str = vs.findAdminByName(loginName);
		System.out.println(str);
		
        	return str;      	
	}
	
	@RequestMapping(value = "/addAdmin")
	public String addAdmin(Admin ad) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(ad.getLoginPwd().getBytes());
		String pwd = new BigInteger(1, md.digest()).toString(16);
		ad.setLoginPwd(pwd);
		System.out.println(ad);
		vs.addAdmin(ad);
		return "redirect:/video/login.action";
	}
	

	
	
	 

}
