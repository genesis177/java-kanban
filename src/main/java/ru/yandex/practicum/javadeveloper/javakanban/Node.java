package ru.yandex.practicum.javadeveloper.javakanban;

class Node {
    Task task;
    Node prev;
    Node next;

    Node(Task task) {
        this.task = task;
    }
}
