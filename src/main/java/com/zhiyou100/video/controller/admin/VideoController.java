package com.zhiyou100.video.controller.admin;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@RequestMapping("/admin/video")
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
		return "admin/video/index";
	}
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String username, String password,HttpSession session) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		String pwd = new BigInteger(1, md.digest()).toString(16);
		Admin ad = vs.login(username,pwd);
		if(ad!=null){
			
			//System.out.println(username+"----"+pwd);
			session.setAttribute("adminName", ad);
			return "forward:/admin/video/videoManage.action";
		}
		return "redirect:/admin/video/login.action";
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
		mav.setViewName("admin/video/videoManage");
					
		return mav;
	}
	//添加视频
	@RequestMapping(value = "/addVideo.action", method = RequestMethod.GET)
	public String addVideo(Model md) {
		List<Speaker> speakerList = ss.findAllSpeaker();
		List<Course>  courseList = cs.findAllCourse();
		md.addAttribute("addspeakerList", speakerList);
		md.addAttribute("addcourseList", courseList);
		
		return "admin/video/addVideo";
	}

	@RequestMapping(value = "/addVideo.action", method = RequestMethod.POST)
	public String addVideo(Video video){
		vs.addVideo(video);
	
		return "forward:/admin/video/videoManage.action";
	}
	
	//修改视频
	@RequestMapping(value = "/editVideo.action", method = RequestMethod.GET)
	public String editVideo(int id,Model md,int page) {
		Video video = vs.findVideoById(id);
		//System.out.println(page);	
		List<Speaker> speakerList = ss.findAllSpeaker();
		List<Course>  courseList = cs.findAllCourse();
		md.addAttribute("editspeakerList", speakerList);
		md.addAttribute("editcourseList", courseList);
		md.addAttribute("video", video);
		md.addAttribute("page", page);
		return "admin/video/editVideo";
	}

	@RequestMapping(value = "/editVideo.action", method = RequestMethod.POST)
	public String editVideo(Video video,int page){
		//System.out.println(page);
		vs.editVideo(video);
	
		return "forward:/admin/video/videoManage.action";
	}
	
	//删除视频
	@RequestMapping(value = "/deleteVideo.action", method = RequestMethod.POST)
	@ResponseBody
	public String deleteVideo(int id) {
		System.out.println(id);
		vs.deleteVideo(id);
		return "success";
	}
	@RequestMapping(value = "/deleteAll.action", method = RequestMethod.GET)
	public String deleteAllVideo(Integer[] rowCheck) {
		System.out.println(Arrays.toString(rowCheck));
		List<Integer> list = Arrays.asList(rowCheck);
		
		vs.deleteVideoByIds(list);
		//vs.deleteVideoByIds(rowCheck);
		return "forward:/admin/video/videoManage.action";
	}
	@RequestMapping(value = "/videoAnalysis.action")
	public String videoAnalysis(Model md) {
		StringBuffer data= new StringBuffer();
		StringBuffer time= new StringBuffer();
		List<VideoTimes>  times = vs.getAverageVideoNames();
		//System.out.println(times);
        for(int i=0;i<times.size();i++){
        	VideoTimes vt = times.get(i);
        	data.append(vt.getNames());
        	time.append(vt.getTimes());
        	if(i<times.size()-1){
        		data.append(",");
        		time.append(",");
        	}
        }

		md.addAttribute("data", data.toString());
		md.addAttribute("time", time.toString());
		return "admin/video/analysis";
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
	@RequestMapping(value = "/exitController.action")
	public String exitController(HttpSession session) {
		session.invalidate();
		return "forward:/admin/video/login.action";
	}
	
	
	//注册
	
	@RequestMapping(value = "/regist.action")

	public String regist() {
		
		return "admin/video/regist";
	}
	@RequestMapping(value = "/regist.action",method=RequestMethod.POST)
	@ResponseBody
	public String regist(String loginName) {
		System.out.println(loginName);
		String str = vs.findAdminByName(loginName);
		System.out.println(str);
		
        	return str;      	
	}
	
	@RequestMapping(value = "/addAdmin.action")
	public String addAdmin(Admin ad) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(ad.getLoginPwd().getBytes());
		String pwd = new BigInteger(1, md.digest()).toString(16);
		ad.setLoginPwd(pwd);
		System.out.println(ad);
		vs.addAdmin(ad);
		return "redirect:/admin/video/login.action";
	}
	

	
	
	 

}
