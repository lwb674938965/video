package com.zhiyou100.video.service;

import com.zhiyou100.video.model.Mail;

public interface MailService {

	void addMailActiveNum(Mail mail);

	Mail findNumByMail(Mail mail);

}
