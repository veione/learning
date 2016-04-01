package com.luo.demo.algorithm;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午2:28
 */
public class LinkNodeUtil {
    public static void print(Node node){
        while (node!=null){
            System.out.println(node.value);
            node=node.next;
        }
    }

    public static Node buildNode(int start,int len) {
        Node head = new Node(start);
        Node cur = head;
        for (int i = 0; i < len; i++) {
            Node node = new Node(++start);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public static void main(String[] args) {
        print(buildNode(1,6));
    }

    public static Node reverseNode(Node curl) {
        if(curl==null || curl.next==null){
            return curl;
        }
        Node prev = null;
        Node next = null;
        while (curl.next!=null){
            next = curl.next;
            curl.next = prev;
            prev = curl;
            curl = next;
        }
        return prev;
    }
}
