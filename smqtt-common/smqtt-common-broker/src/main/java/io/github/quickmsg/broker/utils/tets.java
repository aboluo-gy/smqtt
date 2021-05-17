package io.github.quickmsg.broker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author Shihuan Sun
 * @Email 13733918655@163.com
 * @Date 2021/2/2 17:06
 * @Version 1.0
 */
public class tets {
    public static void main(String[] args){
        String str = "appIdguideShopapiNamegetGuideShopNoticeListtimestamp1598340549495";
        Pattern p= Pattern.compile("appId(\\w+)apiName");
        Matcher m=p.matcher(str);
        while(m.find()){
            System.out.println(m.group(1));

        }


    }
}
