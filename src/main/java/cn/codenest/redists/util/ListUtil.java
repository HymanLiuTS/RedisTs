package cn.codenest.redists.util;

import java.util.List;

/**
 * @author ：Hyman
 * @date ：Created in 2020/4/8 18:31
 * @description：列表操作泛型
 * @modified By：
 * @version: v1.0$
 */
public class ListUtil<T> {

    public void addElement(List<T> list, T t) {
        if(list!=null){
            list.add(t);
        }
    }

    public void removeElement(List<T> list, T t) {
        if(list!=null){
            list.remove(t);
        }
    }

    private static ListUtil instance;

    public static ListUtil getInstance() {
        if (instance == null) {
            synchronized (ListUtil.class) {
             if(instance==null){
                 instance=new ListUtil();
             }
            }
        }
        return instance;
    }
}
