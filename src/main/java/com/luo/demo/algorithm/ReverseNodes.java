package com.luo.demo.algorithm;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午2:56
 */
public class ReverseNodes {
    public static void main(String[] args) {
        Node head = LinkNodeUtil.buildNode(1,5);
        LinkNodeUtil.print(head);
        LinkNodeUtil.print(LinkNodeUtil.reverseNode(head));
    }
}
