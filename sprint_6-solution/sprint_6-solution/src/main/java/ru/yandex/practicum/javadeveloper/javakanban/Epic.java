package ru.yandex.practicum.javadeveloper.javakanban;


import java.util.ArrayList;


import java.util.List;


public class Epic extends Task {


    private List<Integer> subtaskIds;


    public Epic(String title, String description) {


        super(title, description);


        this.subtaskIds = new ArrayList<>();


    }


    public void addSubtask(int subtaskId) {


        subtaskIds.add(subtaskId);


    }


    public List<Integer> getSubtaskIds() {


        return subtaskIds;


    }


    public void clearSubtasks() {


        subtaskIds.clear();


    }


}