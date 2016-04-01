package com.luo.demo.algorithm;

import java.util.*;

public class TextFilterUtil {

    //敏感词库
    private static HashMap sensitiveWordMap = null;
    //默认编码格式
    private static final String ENCODING = "gbk";
    //敏感词库的路径
    /**
     * 初始化敏感词库
     */
    private static void init() {
        //读取文件
        Set<String> keyWords = readSensitiveWords();
        //创建敏感词库
        sensitiveWordMap = new HashMap<>(keyWords.size());
        for (String keyWord : keyWords) {
            createKeyWord(keyWord);
        }
    }

    /**
     * 构建敏感词库
     *
     * @param keyWord
     */
    private static void createKeyWord(String keyWord) {
        if (sensitiveWordMap == null) {
            return;
        }
        Map nowMap = sensitiveWordMap;
        for (Character c : keyWord.toCharArray()) {
            Object obj = nowMap.get(c);
            if (obj == null) {
                Map<String, Object> childMap = new HashMap<>();
                childMap.put("isEnd", "false");
                nowMap.put(c, childMap);
                nowMap = childMap;
            } else {
                nowMap = (Map) obj;
            }
        }
        nowMap.put("isEnd", "true");
    }

    /**
     * 读取敏感词文件
     *
     * @return
     */
    private static Set<String> readSensitiveWords() {
        Set<String> keyWords = new HashSet<>();
        keyWords.add("张三");
        keyWords.add("历史");
        return keyWords;
    }

    /**
     * 检查敏感词
     *
     * @return
     */
    private static List<String> checkSensitiveWord(String text) {
        if (sensitiveWordMap == null) {
            init();
        }
        List<String> sensitiveWords = new ArrayList<>();
        Map nowMap = sensitiveWordMap;
        for (int i = 0; i < text.length(); i++) {
            Character word = text.charAt(i);
            Object obj = nowMap.get(word);
            if (obj == null) {
                continue;
            }
            int j = i + 1;
            Map childMap = (Map) obj;
            while (j < text.length()) {
                if ("true".equals(childMap.get("isEnd"))) {
                    sensitiveWords.add(text.substring(i, j));
                }
                obj = childMap.get(text.charAt(j));
                if (obj != null) {
                    childMap = (Map) obj;
                } else {
                    break;
                }
                j++;
            }
        }
        return sensitiveWords;
    }

    public static void main(String[] args) {
        TextFilterUtil.init();
        TextFilterUtil.checkSensitiveWord("我不是很懂历史故事");
    }
}
