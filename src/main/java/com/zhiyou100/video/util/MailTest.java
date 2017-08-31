package com.zhiyou100.video.util;

import org.junit.Test;

/**  
* @ClassName: MailTest  
* @Description: TODO
* @author lyb  
* @date 2017年8月28日  下午2:31:54
*  
*/
public class MailTest {

	@Test
	public void test01() throws Exception{
		/*
		 * 
		 * <a href=\"http://www.baidu.com\">百度</a>
		 */
		MailUtil.send("1585019349@qq.com", "恭喜你获得万元大奖....", "恭喜你获得万元大奖,请输入银行卡号及密码领取");
		/*
		 * 0. MD5(邮箱+密码+当前时间)就是激活码  24小时有效
		 * 1.id(uuid)  邮箱地址   状态(0未激活,1表示激活)   激活码     失效时间
		 * 2.http://localhost:8080/Test/Mail/active.action?id=id&activeCode=激活码 
		 */
		
		
		
	}
	
}
