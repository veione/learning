package com.luo.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luohui
 *         Date: 16/3/31
 *         Time: 下午2:23
 *         合并 K 个已排序链表 并且将其排序并返回。
 *         我们采用分治的方法来解决这个问题，其有 K 个链表，不断将其划分（partition），再将其归并（merge）。
           划分的部分并不难，将其不断分成两部分，但是需要注意的是可能出现 start 和 end 相等的情况，这时候就直接 return lists[start] 就可以了。
 */
public class MergeSortedLists {
    public static void main(String[] args) {
        Node node1 = LinkNodeUtil.buildNode(1, 5);
        Node node2 = LinkNodeUtil.buildNode(3, 5);
        Node node3 = LinkNodeUtil.buildNode(4, 5);
        List<Node> nodes = new ArrayList<>(3);
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        LinkNodeUtil.print(mergeSortedLists(nodes));
    }

    private static Node mergeSortedLists(List<Node> nodes) {
        int len = nodes.size();
        Node node = null;
        while (len>0){
            Node left = nodes.get(--len);
            Node newNode = mergeTwoSortedLists(node,left);
            node = newNode;
        }
        return node;
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
