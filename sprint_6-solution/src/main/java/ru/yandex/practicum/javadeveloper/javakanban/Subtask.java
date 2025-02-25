package ru.yandex.practicum.javadeveloper.javakanban;


public class Subtask extends Task {


    private int parentEpicId;


    public Subtask(String title, String description, int parentEpicId) {


        super(title, description);


        this.parentEpicId = parentEpicId;


    }


    public int getParentEpicId() {


        return parentEpicId;


    }


}