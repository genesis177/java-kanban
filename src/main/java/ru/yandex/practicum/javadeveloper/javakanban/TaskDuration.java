package ru.yandex.practicum.javadeveloper.javakanban;

import java.time.Duration;
import java.util.List;

public class TaskDuration {
    private Duration duration;

    public TaskDuration() {
        this.duration = Duration.ZERO;
    }

    public void add(Duration additionalDuration) {
        if (additionalDuration != null) {
            duration = duration.plus(additionalDuration);
        }
    }

    public Duration getTotalDuration() {
        return duration;
    }

    public static TaskDuration fromSubtasks(List<Integer> subtaskIds) {
        TaskDuration taskDuration = new TaskDuration();
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId);
            if (subtask != null) {
                taskDuration.add(subtask.getDuration());
            }
        }
        return taskDuration;
    }
}
