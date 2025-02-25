package ru.yandex.practicum.javadeveloper.javakanban;


import java.util.ArrayList;


import java.util.HashMap;


import java.util.List;


import java.util.Map;


public class InMemoryTaskManager implements TaskManager {


    private final HistoryManager historyManager = Managers.getDefaultHistory();


    private final Map<Integer, Task> tasks = new HashMap<>();


    private final Map<Integer, Subtask> subtasks = new HashMap<>();


    private final Map<Integer, Epic> epics = new HashMap<>();


    private int idCounter = 1;


    @Override


    public void createTask(Task task) {


        task.setId(idCounter++);


        tasks.put(task.getId(), task);


    }


    @Override


    public void createEpic(Epic epic) {


        epic.setId(idCounter++);


        epics.put(epic.getId(), epic);


    }


    @Override


    public void createSubtask(Subtask subtask) {


        subtask.setId(idCounter++);


        subtasks.put(subtask.getId(), subtask);


        Epic epic = getEpic(subtask.getParentEpicId());


        if (epic != null) {


            epic.addSubtask(subtask.getId());


            updateEpicStatus(epic);


        }


    }


    @Override


    public Task getTask(int id) {


        Task task = tasks.get(id);


        if (task != null) {


            addToHistory(task);


        }


        return task;


    }


    @Override


    public Subtask getSubtask(int id) {


        Subtask subtask = subtasks.get(id);


        if (subtask != null) {


            addToHistory(subtask);


        }


        return subtask;


    }


    @Override


    public Epic getEpic(int id) {


        Epic epic = epics.get(id);


        if (epic != null) {


            addToHistory(epic);


        }


        return epic;


    }


    @Override


    public void deleteEpic(int id) {


        Epic epic = epics.remove(id);


        if (epic != null) {


            for (Integer subtaskId : epic.getSubtaskIds()) {


                subtasks.remove(subtaskId);


            }


        }


    }


    @Override


    public List<Task> getHistory() {


        return historyManager.getHistory();


    }


    @Override


    public void deleteTask(int id) {


        tasks.remove(id);


    }


    @Override


    public void deleteSubtask(int id) {


        Subtask subtask = subtasks.remove(id);


        if (subtask != null) {


            Epic epic = getEpic(subtask.getParentEpicId());


            if (epic != null) {


                epic.clearSubtasks(); // Удаляем из эпика


            }


        }


    }


    @Override


    public List<Task> getAllTasks() {


        return new ArrayList<>(tasks.values());


    }


    @Override


    public List<Subtask> getAllSubtasks() {


        return new ArrayList<>(subtasks.values());


    }


    @Override


    public List<Epic> getAllEpics() {


        return new ArrayList<>(epics.values());


    }


    @Override


    public void updateTask(Task task) {


        tasks.put(task.getId(), task);


    }


    @Override


    public void updateSubtask(Subtask subtask) {


        subtasks.put(subtask.getId(), subtask);


        Epic epic = getEpic(subtask.getParentEpicId());


        if (epic != null) {


            updateEpicStatus(epic);


        }


    }


    @Override


    public void updateEpic(Epic epic) {


        epics.put(epic.getId(), epic);


        updateEpicStatus(epic);


    }


    private void addToHistory(Task task) {


        historyManager.add(task);


    }


    private void updateEpicStatus(Epic epic) {


        List<Integer> subtaskIds = epic.getSubtaskIds();


        if (subtaskIds.isEmpty()) {


            epic.setStatus(Status.NEW);


            return;


        }


        boolean allDone = true;


        boolean anyInProgress = false;


        for (Integer subtaskId : subtaskIds) {


            Subtask subtask = subtasks.get(subtaskId);


            if (subtask != null) {


                if (subtask.getStatus() != Status.DONE) {


                    allDone = false;


                }


                if (subtask.getStatus() == Status.IN_PROGRESS) {


                    anyInProgress = true;


                }


            }


        }


        if (allDone) {


            epic.setStatus(Status.DONE);


        } else if (anyInProgress) {


            epic.setStatus(Status.IN_PROGRESS);


        } else {


            epic.setStatus(Status.NEW);


        }


    }


}