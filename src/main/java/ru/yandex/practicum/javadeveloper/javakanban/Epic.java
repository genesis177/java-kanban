package ru.yandex.practicum.javadeveloper.javakanban;

import java.time.Duration;
import java.time.LocalDateTime;
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

    @Override
    public Duration getDuration() {
        Duration totalDuration = Duration.ZERO;
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId);
            if (subtask != null) {
                totalDuration = totalDuration.plus(subtask.getDuration());
            }
        }
        return totalDuration;
    }

    @Override
    public LocalDateTime getStartTime() {
        LocalDateTime earliestStart = null;
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId);
            if (subtask != null) {
                LocalDateTime subtaskStart = subtask.getStartTime();
                if (subtaskStart != null && (earliestStart == null || subtaskStart.isBefore(earliestStart))) {
                    earliestStart = subtaskStart;
                }
            }
        }
        return earliestStart;
    }

    @Override
    public LocalDateTime getEndTime() {
        LocalDateTime latestEnd = null;
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId);
            if (subtask != null) {
                LocalDateTime subtaskEnd = subtask.getEndTime();
                if (subtaskEnd != null && (latestEnd == null || subtaskEnd.isAfter(latestEnd))) {
                    latestEnd = subtaskEnd;
                }
            }
        }
        return latestEnd;
    }
}