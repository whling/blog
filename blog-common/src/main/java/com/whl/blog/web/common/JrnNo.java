package com.whl.blog.web.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by whling on 2017/6/21.
 *
 */
public class JrnNo {

    public static String gen() {
        return (new StringBuilder())
                .append(DateUtil.getCurDteTimeNoSign())
                .append(ThreadLocalRandom.current().nextInt(100, 999))
                .toString();
    }
    
    public static String getJrnNo(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String requestId = sdf.format(new Date());
		String JRN_NO = requestId + ThreadLocalRandom.current().nextInt(1, 10);
		return JRN_NO;
    }
    public static void main(String[] args) {
		System.out.println(gen());
		System.out.println(getJrnNo());
	}

    public static String gen(int len) {
        return gen().substring(0, len);
    }
}
