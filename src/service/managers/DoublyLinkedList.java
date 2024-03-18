package service.managers;

import service.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    private Map<Integer, Node> idToNodeMap;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.idToNodeMap = new HashMap<>();
    }

    private static class Node {
        private Task task;
        private Node prev;
        private Node next;

        public Node(Task task) {
            this.task = task;
        }
    }

    public void add(Task task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        idToNodeMap.put(task.getId(), newNode);
        size++;
    }

    public void remove(int id) {
        Node nodeToRemove = idToNodeMap.get(id);

        if (nodeToRemove == null) {
            return;
        }

        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }

        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }

        idToNodeMap.remove(id);
        size--;
    }

    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>(size);
        Node current = head;
        while (current != null) {
            history.add(current.task);
            current = current.next;
        }
        return history;
    }

    public int getSize() {
        return size;
    }
}