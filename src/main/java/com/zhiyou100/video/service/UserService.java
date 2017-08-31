package com.zhiyou100.video.service;

import com.zhiyou100.video.model.User;

public interface UserService {

	User findUserByNameAndPwd(User u);

	void regist(User u);

	User findUserById(Integer id);

	User updateUserById(User u);

	User updatePassword(User u);

	void updatePasswordByEmail(User u);

	int getActivenum(String email) throws Exception;

	User findUserByEmailAndNum(User u);

	User findUserByEmail(String email);

}
