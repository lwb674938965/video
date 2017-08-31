package com.zhiyou100.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou100.video.mapper.MailMapper;
import com.zhiyou100.video.model.Mail;
import com.zhiyou100.video.model.MailExample;
import com.zhiyou100.video.service.MailService;
@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	MailMapper mm;

	@Override
	public void addMailActiveNum(Mail mail) {
		mm.insertSelective(mail);
		
	}

	@Override
	public Mail findNumByMail(Mail mail) {
		MailExample me = new MailExample();
		me.createCriteria().andEmailEqualTo(mail.getEmail()).andActivenumEqualTo(mail.getActivenum());
		if(mm.selectByExample(me).isEmpty()){
			return null;
		}
		return mm.selectByExample(me).get(0);
	}

}
