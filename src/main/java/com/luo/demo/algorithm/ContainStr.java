package com.luo.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午5:48
 */
public class ContainStr {
    private int[] keywordsArray;
    private Map<String,Integer> map;
    private int len;

    public ContainStr(String[] keywords) {
        this.len = keywords.length;
        this.keywordsArray = new int[len];
        this.map = keywordMap(keywords);
    }

    private Map<String, Integer> keywordMap(String[] keywords) {
        Map<String, Integer> map = new HashMap<>(len);
        for (int j = 0; j < len; j++) {
            map.put(keywords[j],j);
        }
        return map;
    }

    public static void main(String[] args) {
        String description="hello software hello test world spring sun flower hello";
        String[] keywords = {"hello","world"};
        ContainStr nAbstract = new ContainStr(keywords);
        System.out.println(nAbstract.extractSummary(description, keywords));
    }

    private boolean extractSummary(String description, String[] keywords) {
        return false;
    }
}
