package com.zhiyou100.video.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou100.video.model.Page;
import com.zhiyou100.video.model.Speaker;
import com.zhiyou100.video.service.SpeakerService;

@Controller
@RequestMapping("/admin/speaker")
public class SpeakerController {
	
	@Autowired
	SpeakerService ss;
	
	//主讲人列表
	@RequestMapping("/speakerList.action")
	public ModelAndView speakerList(@RequestParam(defaultValue="",required=true)String speakerName,
			@RequestParam(defaultValue="",required=true)String speakerJob,
			@RequestParam(value="page",defaultValue="1",required=true)int currentPage,
			
			HttpServletRequest req) {
		req.setAttribute("speakerName", speakerName);
		req.setAttribute("speakerJob", speakerJob);
		
		
		Page<Speaker> page = ss.findAllSpeakerByConditions(speakerName,speakerJob,currentPage);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", page);
		mav.setViewName("admin/speaker/speakerList");
					
		return mav;
	}
	//添加主讲人
	@RequestMapping(value = "/addSpeaker", method = RequestMethod.GET)
	public String addSpeaker() {
		return "admin/speaker/addSpeaker";
	}

	@RequestMapping(value = "/addSpeaker", method = RequestMethod.POST)
	public String addSpeaker(Speaker speaker){
		ss.addSpeaker(speaker);
		return "redirect:/admin/speaker/speakerList.action";
	}
	//修改主讲人信息
	@RequestMapping(value = "/editSpeaker.action", method = RequestMethod.GET)
	public String editSpeaker(int id,Model md,int page){
		Speaker sp = ss.findSpeakerById(id);
		md.addAttribute("sp", sp);
		md.addAttribute("page", page);
		return "admin/speaker/editSpeaker";
	}
	@RequestMapping(value = "/editSpeaker.action", method = RequestMethod.POST)
	public String editSpeaker(Speaker speaker,int page){
		ss.editSpeaker(speaker);
		return "redirect:/admin/speaker/speakerList.action?page="+page;
	}
	//删除主讲人
	@RequestMapping(value = "/deleteSpeaker.action", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSpeaker(int id){
		ss.deleteSpeakerById(id);	
		return "success";
	}

}
