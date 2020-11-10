package cn.codenest.redists.bloom;

/**
 * <h3>springbootts</h3>
 * <p></p>
 *
 * @author : Hyman
 * @date : 2020-08-18 11:18
 **/
public interface MyBloomFilter {

    public boolean isContainKey(String key);

    public void putKey(String key);

}
