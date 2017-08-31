package com.zhiyou100.video.controller.front;

import java.io.File;
import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyou100.video.model.User;
import com.zhiyou100.video.service.MailService;
import com.zhiyou100.video.service.UserService;

@Controller
@RequestMapping("/front/user")
public class UserLoginController {

	@Autowired
	UserService us;
	@Autowired
	MailService ms;
	
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	@ResponseBody
	public String login(User u,HttpSession session){
		//System.out.println(u.getPassword());
		User user = us.findUserByNameAndPwd(u);
		if(user != null){
			session.setAttribute("_front_user", user);
			return "success";
		}
		
		return "false";
	}
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login(HttpServletRequest req){
		//System.out.println(req.getServletPath());
		return "front/index";
	}
	
	
	@RequestMapping(value="/regist.do",method=RequestMethod.POST)
	@ResponseBody
	public String regist(User u,HttpSession session){
		u.setInsertTime(new Timestamp(System.currentTimeMillis()));
		us.regist(u);
		User user = us.findUserByNameAndPwd(u);
		if(user != null){
			session.setAttribute("_front_user", user);
			return "success";
		}
		return "false";
	}
	
	@RequestMapping(value="/index.do")
	public String index(Integer id,Model mod){
		User u = us.findUserById(id);
		mod.addAttribute("user", u);
		return "front/user/index";
	}
	
	@RequestMapping(value="/profile.do",method=RequestMethod.GET)
	public String profile(Integer id,Model md){
		User u = us.findUserById(id);
		md.addAttribute("user", u);
		return "front/user/profile";
	}
	
	@RequestMapping(value="/profile.do",method=RequestMethod.POST)
	public String profile(User u,Model md){
		//System.out.println(u);
		u.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		User user = us.updateUserById(u);
		md.addAttribute("user", user);
		return "front/user/index";
	}
	
	@RequestMapping("/logout.do")  
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:/front/user/login.do";
	}
	
	@RequestMapping(value="/avatar.do",method=RequestMethod.GET)  
	public String avatar(Integer id,Model md){
		User u = us.findUserById(id);
		md.addAttribute("user", u);
		return "front/user/avatar";
	}
	
	@RequestMapping(value="/avatar.do",method=RequestMethod.POST)  
	public String avatar(MultipartFile image_file,User u,HttpSession session,Model md) throws Exception{
		String str = UUID.randomUUID().toString().replaceAll("-", "");
		String ext = FilenameUtils.getExtension(image_file.getOriginalFilename());
		String fileName = str+"."+ext;
		u.setHeadUrl(fileName);
		String path = "D:\\upload";
		image_file.transferTo(new File(path+"\\"+fileName));
		User user = us.updateUserById(u);
		session.setAttribute("_front_user", user);
		md.addAttribute("user", user);
		//System.out.println(user);
		return "front/user/index";
	}
	
	@RequestMapping(value="/password.do",method=RequestMethod.GET)  
	public String password(Integer id,Model md){
		User u = us.findUserById(id);
		md.addAttribute("user", u);
		return "front/user/password";
	}
	
	 @RequestMapping(value="/password.do",method=RequestMethod.POST)  
	public String editPassword(User u,Model md){
		String message = null;
		//System.out.println(u);
		User user = us.findUserByNameAndPwd(u);
		//System.out.println(user);
		if(user==null){
			User u1 = us.findUserById(u.getId());			
			md.addAttribute("user", u1);
			md.addAttribute("warning", "旧密码输入错误");
			return "front/user/password";
		}
		us.updatePassword(u);
		message="修改成功";
		md.addAttribute("message", message);
		return "front/user/password";
	}
	
	 @RequestMapping("/forgetpwd.do")
	 public String forgetpwd(){
		 return "front/user/forget_pwd";
	 }
	 @RequestMapping(value="/forgetpwd.do",method=RequestMethod.POST)
	 public String getPwd(User u,Model md){
		 User user = us.findUserByEmailAndNum(u);
		 //System.out.println(mail);
		 //System.out.println(ml);
		 if(user != null){
			 md.addAttribute("email",u.getEmail());
			 md.addAttribute("captcha",u.getCaptcha());
			 return "front/user/reset_pwd";
		 }
		 return "redirect:/front/user/forgetpwd.do";
	 }
	 
	 @RequestMapping(value="/resetpwd.do",method=RequestMethod.POST)
	 public String resetpwd(User u,Model md){
		 us.updatePasswordByEmail(u);
		 return "front/index";
	 }
	 
	 @RequestMapping(value="/sendMail.do",method=RequestMethod.POST)
	 @ResponseBody
	 public String sendMail(String email) throws Exception{
		 int num = us.getActivenum(email);
		 return ""+num;
	 }
/*	 @RequestMapping(value="/sendMail.do",method=RequestMethod.POST)
	 public String sendMail(String email) throws Exception{
		 System.out.println(email);
		 Mail mail = new Mail();
		 mail.setEmail(email);
		 mail.setStatus(0);
		 String str = UUID.randomUUID().toString().replaceAll("-", "");
		 mail.setId(str);
		 String activeId = MD5Utils.getMD5(email+new Time(System.currentTimeMillis()));
		 mail.setActivenum(activeId);
		 ms.addMailActiveNum(mail);
		 MailUtil.send("674938965@qq.com", "找回密码", "");
		 
		 return "front/user/forget_pwd";
	 }
*/	
	 @RequestMapping(value="/birthdayAttention.do",method=RequestMethod.POST)
	 public String birthdayAttention(User u,Model md){
		 us.updatePasswordByEmail(u);
		 return "front/index";
	 }
	 @RequestMapping(value="/checkEmail.do")
	 @ResponseBody
	 public String checkEmail(String email){
		//System.out.println(111);
		User u = us.findUserByEmail(email);
		//System.out.println(u);
		if(u!=null){
			return "fail";
		}
		return "success";
	 }
	 
	 
	 
}
