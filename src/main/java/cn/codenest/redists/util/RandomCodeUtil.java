package cn.codenest.redists.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @author ：Hyman
 * @date ：Created in 2020/4/26 9:55
 * @description：
 * @modified By：
 * @version: $
 */
public class RandomCodeUtil {

    public static String getCode(String first){
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        String str=String.format("%s%s%s",first,now.format(dateTimeFormatter),getRandomString(4));
        return str;
    }

    /**
     * 获取指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append(String.valueOf((char) result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }


}
