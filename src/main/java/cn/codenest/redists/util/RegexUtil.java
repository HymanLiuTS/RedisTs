package cn.codenest.redists.util;

import cn.hutool.core.convert.Convert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：Hyman
 * @date ：Created in 2020/4/15 17:29
 * @description：
 * @modified By：
 * @version: $
 */
public class RegexUtil {

    private static String number = "[^0-9]";
    private static ConcurrentHashMap<String, Pattern> map = new ConcurrentHashMap();

    public static Integer GetNumber( String str){
        Pattern pattern = null;
        if (map.contains(number)) {
            pattern = map.get(number);
        } else {
            pattern = Pattern.compile(number);
            map.put(number, pattern);
        }
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            return Convert.toInt(matcher.replaceAll("").trim());
        }
        return null;
    }

    public static Boolean isMatched(String str, String reg) {
        Pattern pattern = null;
        if (map.contains(reg)) {
            pattern = map.get(reg);
        } else {
            pattern = Pattern.compile(reg);
            map.put(reg, pattern);
        }
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(GetNumber("${SCHEDULE_MINUTES:1}"));
    }
}
