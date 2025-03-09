package ru.yandex.practicum.javadeveloper.javakanban;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileBackedTaskManager extends AbstractFileBackedTaskManager {

    public FileBackedTaskManager(File file) {
        super(file);
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines.subList(1, lines.size())) {
                Task task = fromString(line);
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
            throw new RuntimeException("Ошибка загрузки из файла", e);
        }
        return manager;
    }

    @Override
    public Task getTask(int id) {
        return null;
    }

    @Override
    public Subtask getSubtask(int id) {
        return null;
    }

    @Override
    public Epic getEpic(int id) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return List.of();
    }

    @Override
    public List<Epic> getAllEpics() {
        return List.of();
    }

    @Override
    public List<Task> getHistory() {
        return List.of();
    }
}

abstract class AbstractFileBackedTaskManager implements TaskManager {
    private final File file;

    public AbstractFileBackedTaskManager(File file) {
        this.file = file;
    }

    @Override
    public void createTask(Task task) {
        super.getClass();
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.getClass();
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        try {
            super.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.getClass();
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.equals(subtask);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        try {
            super.wait(epic.getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        save();
    }

    @Override
    public void deleteTask(int id) {
        super.getClass();
        save();
    }

    @Override
    public void deleteSubtask(int id) {
        super.getClass();
        save();
    }

    @Override
    public void deleteEpic(int id) {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        save();
    }



    private void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("id,type,name,status,description,epic");


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

    private String toString(Task task) {
        return task.getId() + ",TASK," + task.getTitle() + "," + task.getStatus() + "," + task.getDescription() + ",";
    }

    private String toString(Subtask subtask) {
        return subtask.getId() + ",SUBTASK," + subtask.getTitle() + "," + subtask.getStatus() + "," + subtask.getDescription() + "," + subtask.getParentEpicId();
    }

    private String toString(Epic epic) {
        return epic.getId() + ",EPIC," + epic.getTitle() + "," + epic.getStatus() + "," + epic.getDescription() + ",";
    }

    static Task fromString(String value) {
        String[] parts = value.split(",");
        int id = Integer.parseInt(parts[0]);
        String type = parts[1];
        String title = parts[2];
        Status status = Status.valueOf(parts[3]);
        String description = parts[4];

        if ("TASK".equals(type)) {
            Task task = new Task(title, description);
            task.setId(id);
            task.setStatus(status);
            return task;
        } else if ("SUBTASK".equals(type)) {
            int epicId = Integer.parseInt(parts[5]);
            Subtask subtask = new Subtask(title, description, epicId);
            subtask.setId(id);
            subtask.setStatus(status);
            return subtask;
        } else if ("EPIC".equals(type)) {
            Epic epic = new Epic(title, description);
            epic.setId(id);
            epic.setStatus(status);
            return epic;
        }
        return null;
    }
}








