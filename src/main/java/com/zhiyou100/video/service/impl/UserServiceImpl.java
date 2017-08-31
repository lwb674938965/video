package com.zhiyou100.video.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.UserMapper;
import com.zhiyou100.video.model.User;
import com.zhiyou100.video.model.UserExample;
import com.zhiyou100.video.service.UserService;
import com.zhiyou100.video.util.IdentifyingCode;
import com.zhiyou100.video.util.MailUtil;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper um;

	@Override
	public User findUserByNameAndPwd(User u) {
		UserExample us = new UserExample();
		us.createCriteria().andEmailEqualTo(u.getEmail()).andPasswordEqualTo(u.getPassword());
		if( um.selectByExample(us).isEmpty()){
			return null;			
		}
		return um.selectByExample(us).get(0);
	}

	@Override
	public void regist(User u) {
		um.insert(u);
		
	}

	@Override
	public User findUserById(Integer id) {
		
		return um.selectByPrimaryKey(id);
	}

	@Override
	public User updateUserById(User u) {
		
		um.updateByPrimaryKeySelective(u);
		//System.out.println(um.selectByPrimaryKey(u.getId()));
		return um.selectByPrimaryKey(u.getId());
	}

	@Override
	public User updatePassword(User u) {

		um.updatePassWordById(u);
		return um.selectByPrimaryKey(u.getId());
	}

	@Override
	public void updatePasswordByEmail(User u) {
		um.updatepasswordByEmail(u);
		
	}

	@Override
	public int getActivenum(String email) throws Exception {
		User u = new User();
		int num = IdentifyingCode.getCode(8);
		u.setEmail(email);
		u.setCaptcha(""+num);
		um.addNumByEmail(u);
		MailUtil.send("674938965@qq.com", "找回密码", ""+num);;
		return num;
	}

	@Override
	public User findUserByEmailAndNum(User u) {
		UserExample ue = new UserExample();
		ue.createCriteria().andEmailEqualTo(u.getEmail()).andCaptchaEqualTo(u.getCaptcha());
		List<User> list = um.selectByExample(ue);
		if(list.isEmpty()){
			return null;			
		}
		return list.get(0);
	}

	@Override
	public User findUserByEmail(String email) {
		User u = um.findUserByMail(email);
		return u;
	}
	
	
}
