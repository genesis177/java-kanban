package ru.yandex.practicum.javadeveloper.javakanban;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.attribute.PosixFilePermissions.fromString;

public class FileBackedTaskManager extends InMemoryTaskManager  {

    private File file;

    public FileBackedTaskManager(File file) {
        super();
    }

    public static FileBackedTaskManager loadFromFile(File file) throws ManagerSaveException {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines.subList(1, lines.size())) {
                Task task = (Task) fromString(line);
                if (task != null) {
                    if (task instanceof Epic) {
                        manager.createEpic((Epic) task);
                    } else if (task instanceof Subtask) {
                        manager.createSubtask((Subtask) task);
                    } else {
                        manager.createTask(task);
                    }
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка загрузки из файла", e);
        }
        return manager;
    }

    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save(); 
    }


    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }


    private String toString(Task task) {
        return task.getId() + ",TASK," + task.getTitle() + "," + task.getStatus() + "," + task.getDescription() + ","
                + task.getDuration().toMinutes() + "," + (task.getStartTime() != null ? task.getStartTime() : "");
    }

    private boolean isOverlapping(Task task1, Task task2) {
        LocalDateTime start1 = task1.getStartTime();
        LocalDateTime end1 = task1.getEndTime();
        LocalDateTime start2 = task2.getStartTime();
        LocalDateTime end2 = task2.getEndTime();

        return (start1 != null && end1 != null && start2 != null && end2 != null) &&
                (start1.isBefore(end2) && start2.isBefore(end1));

    }

    private void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("id,type,name,status,description,duration,startTime");

        for (Task task : getAllTasks()) {
            sb.append(toString(task)).append("\n");
        }
        for (Subtask subtask : getAllSubtasks()) {
            sb.append(toString(subtask)).append("\n");
        }
        for (Epic epic : getAllEpics()) {
            sb.append(toString(epic)).append("\n");
        }

        try {
            Files.writeString(file.toPath(), sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения в файл", e);
        }
    }
}









