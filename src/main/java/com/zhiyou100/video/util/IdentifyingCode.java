package com.zhiyou100.video.util;

import java.util.Random;

public class IdentifyingCode {
	
	public static int getCode(int length){
		Random rand = new Random();
		int code = 0;
		if(length == 4){
			code = rand.nextInt(9999-1000+1)+1000;
			return code;
		}else if(length == 5){
			code = rand.nextInt(99999-10000+1)+10000;
		}else if(length == 6){
			code = rand.nextInt(999999-100000+1)+100000;
		}else if(length == 7){
			code = rand.nextInt(9999999-1000000+1)+1000000;
		}else if(length == 8){
			code = rand.nextInt(99999999-10000000+1)+10000000;
		}		
		return code;
	}
}
