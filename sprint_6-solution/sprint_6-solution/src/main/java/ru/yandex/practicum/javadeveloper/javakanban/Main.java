package ru.yandex.practicum.javadeveloper.javakanban;


public class Main {


    public static void main(String[] args) {


        TaskManager manager = Managers.getDefault();


        Task task1 = new Task("Переезд", "Собрать вещи");


        Task task2 = new Task("Упаковать кошку", "Не забыть про кошку");


        Epic epic = new Epic("Семейный праздник", "Организация праздника");


        Subtask subtask1 = new Subtask("Купить торт", "Купить торт для праздника", epic.getId());


        Subtask subtask2 = new Subtask("Пригласить гостей", "Пригласить всех", epic.getId());


        manager.createTask(task1);


        manager.createTask(task2);


        manager.createEpic(epic);


        manager.createSubtask(subtask1);


        manager.createSubtask(subtask2);


        System.out.println("Задачи: " + manager.getAllTasks());


        System.out.println("Подзадачи: " + manager.getAllSubtasks());


        System.out.println("Эпики: " + manager.getAllEpics());


    }


}