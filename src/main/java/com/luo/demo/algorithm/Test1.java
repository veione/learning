package com.luo.demo.algorithm;

import java.util.*;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午1:16
 */
public class Test1 {
    private static Map map = new HashMap<>();

    public static void main(String[] args) {
        Set<String> keys = new HashSet<>();
        keys.add("张三丰");
        keys.add("历史");
        for (String key : keys) {
            createMap(key);
        }
        checkSensitiveWord("我喜欢的人叫张三好");
        checkSensitiveWord("我喜欢的人叫张三丰");
    }

    private static void createMap(String key) {
        Map nowMap = map;
        for (Character c : key.toCharArray()) {
            Object obj = nowMap.get(c);
            if (obj == null) {
                Map childMap = new HashMap<>();
                childMap.put("isEnd", false);
                nowMap.put(c, childMap);
                nowMap = childMap;
            } else {
                nowMap = (Map) obj;
            }
        }
        nowMap.put("isEnd", true);
    }


    public static void checkSensitiveWord(String text) {
        List<String> sensitiveWords = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Object obj = map.get(c);
            if (obj == null) {
                continue;
            }

            int j = i + 1;
            Map childMap = (Map) obj;
            while (j < text.length()) {
                if("true".equals(childMap.get("isEnd"))){
                    sensitiveWords.add(text.substring(i,j));
                }else{
                    Object o = childMap.get(text.charAt(j));
                    if(o!=null){
                        childMap = (Map) o;
                    }else{
                        break;
                    }
                }
                j++;
            }
        }
        System.out.println(Arrays.toString(sensitiveWords.toArray()));
    }
}
