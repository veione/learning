package com.luo.demo.algorithm.link;

/**
 * Created by luohui on 15/11/5.
 */
public class LinkList {
    class Link{
        private int data;
        private Link next;

        public Link(int data) {
            this.data = data;
        }
        public void display(){
            System.out.println(data);
        }
    }


    private Link head;
    public void insertFirst(int data){
        Link link = new Link(data);
        link.next = head;
        head = link;
    }

    public void deleteFirst(){
        head = head.next;
    }

    public void display(){
        Link curr = head;
        while (curr!=null){
            curr.display();
            curr = curr.next;
        }
    }
    public Link find(int key){
        Link cur = head;
        while (cur.data!=key){
            cur = cur.next;
        }
        return cur;
    }
}
