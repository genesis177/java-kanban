package ru.yandex.practicum.javadeveloper.javakanban;

import java.time.Duration;
import java.time.LocalDateTime;
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
    public Duration getDuration(Duration duration) {
        Duration totalDuration = Duration.ZERO;
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId); // Предполагаем, что TaskManager доступен
            if (subtask != null && subtask.getDuration(duration) != null) {
                totalDuration = totalDuration.plus(subtask.getDuration(duration));
            }
        }
        return totalDuration; // Возвращает общую продолжительность
    }

    @Override
    public LocalDateTime getStartTime() {
        LocalDateTime earliestStart = null;
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId);
            if (subtask != null && subtask.getStartTime() != null) {
                if (earliestStart == null || subtask.getStartTime().isBefore(earliestStart)) {
                    earliestStart = subtask.getStartTime();
                }
            }
        }
        return earliestStart; // Возвращает время начала самой ранней подзадачи
    }

    @Override
    public LocalDateTime getEndTime() {
        LocalDateTime latestEnd = null;
        for (Integer subtaskId : subtaskIds) {
            Subtask subtask = (Subtask) TaskManager.getTask(subtaskId);
            if (subtask != null && subtask.getEndTime() != null) {
                if (latestEnd == null || subtask.getEndTime().isAfter(latestEnd)) {
                    latestEnd = subtask.getEndTime();
                }
            }
        }
        return latestEnd; // Возвращает время окончания самой поздней подзадачи
    }
}