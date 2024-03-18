package service.history;

import service.managers.DoublyLinkedList;
import service.tasks.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private static class Node {
        private Task task;
        private Node prev;
        private Node next;

        public Node(Task task) {
            this.task = task;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    private Map<Integer, Node> idToNodeMap;

    public InMemoryHistoryManager() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.idToNodeMap = new HashMap<>();
    }

    public void addTask(Task task) {
        Node existingNode = idToNodeMap.get(task.getId());

        if (existingNode != null) {
            removeNode(existingNode);
        }

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

    public void removeNode(Node nodeToRemove) {
        if (nodeToRemove == null) {
            return;
        }

        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }

        if(nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }

        idToNodeMap.remove(nodeToRemove.task.getId());
        size--;
    }

    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>(size);
        Node current = head;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }

    @Override
    public void add(Task task) {
        addTask(task);
    }

    @Override
    public void remove(int id) {
        Node nodeToRemove = idToNodeMap.get(id);

        if (nodeToRemove == null) {
            return;
        }

        removeNode(nodeToRemove);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}
