package ru.yandex.practicum.javadeveloper.javakanban;

class Node {
   public Task task;
   public Node prev;
   public Node next;

    Node(Task task) {
        this.task = task;
    }
}
