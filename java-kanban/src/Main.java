public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Переезд", "Организовать переезд в новую квартиру");
        Task task2 = new Task("Покупка мебели", "Купить мебель для новой квартиры");
        Epic epic = new Epic("Организация переезда", "Организация всего процесса переезда");
        Subtask subtask1 = new Subtask("Упаковать вещи", "Упаковать все вещи в коробки", epic.getId());
        Subtask subtask2 = new Subtask("Заказать грузчиков", "Заказать грузчиков для переезда", epic.getId());

        taskManager.createTask(task1);
        taskManager.createTask(task2);
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask1);
        taskManager.createSubtask(subtask2);

        System.out.println("Все задачи: " + taskManager.getAllTasks());
        System.out.println("Все эпики: " + taskManager.getAllEpics());
        System.out.println("Все подзадачи: " + taskManager.getAllSubtasks());

        task1.setStatus(Status.IN_PROGRESS);
        subtask1.setStatus(Status.DONE);
        taskManager.updateEpicStatus(epic.getId());
        System.out.println("Статус задачи 1: " + task1.getStatus());
        System.out.println("Статус подзадачи 1: " + subtask1.getStatus());
        System.out.println("Статус эпика: " + epic.getStatus());

        taskManager.deleteTask(task1.getId());
        System.out.println("После удаления задачи 1, все задачи: " + taskManager.getAllTasks());
    }
}