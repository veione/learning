package com.luo.demo.algorithm;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午2:27
 *         3 4 5 6 7
 *         4 5 6 7 8
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Node node1 = LinkNodeUtil.buildNode(1, 5);
        Node node2 = LinkNodeUtil.buildNode(3, 5);
        LinkNodeUtil.print(mergeTwoSortedLists(node1, node2));
    }

    private static Node mergeTwoSortedLists(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.value > node2.value) {
            Node temp = node2;
            temp.next = mergeTwoSortedLists(node1, node2.next);
            return temp;
        } else {
            Node temp = node1;
            temp.next = mergeTwoSortedLists(node1.next, node2);
            return temp;
        }
    }
}
