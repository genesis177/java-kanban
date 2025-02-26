package ru.yandex.practicum.javadeveloper.javakanban;


import java.util.List;


public interface TaskManager {


    /**
     * Создает новую задачу.
     *
     * @param task задача, которую необходимо создать
     */


    void createTask(Task task);


    /**
     * Создает новую подзадачу.
     *
     * @param subtask подзадача, которую необходимо создать
     */


    void createSubtask(Subtask subtask);


    /**
     * Создает новый эпик.
     *
     * @param epic эпик, который необходимо создать
     */


    void createEpic(Epic epic);


    /**
     * Получает задачу по ее идентификатору.
     *
     * @param id идентификатор задачи
     * @return задача с указанным идентификатором
     */


    Task getTask(int id);


    /**
     * Получает подзадачу по ее идентификатору.
     *
     * @param id идентификатор подзадачи
     * @return подзадача с указанным идентификатором
     */


    Subtask getSubtask(int id);


    /**
     * Получает эпик по его идентификатору.
     *
     * @param id идентификатор эпика
     * @return эпик с указанным идентификатором
     */


    Epic getEpic(int id);


    /**
     * Удаляет задачу по ее идентификатору.
     *
     * @param id идентификатор задачи, которую необходимо удалить
     */


    void deleteTask(int id);


    /**
     * Удаляет подзадачу по ее идентификатору.
     *
     * @param id идентификатор подзадачи, которую необходимо удалить
     */


    void deleteSubtask(int id);


    /**
     * Удаляет эпик по его идентификатору.
     *
     * @param id идентификатор эпика, который необходимо удалить
     */


    void deleteEpic(int id);


    /**
     * Получает список всех задач.
     *
     * @return список всех задач
     */


    List<Task> getAllTasks();


    /**
     * Получает список всех подзадач.
     *
     * @return список всех подзадач
     */


    List<Subtask> getAllSubtasks();


    /**
     * Получает список всех эпиков.
     *
     * @return список всех эпиков
     */


    List<Epic> getAllEpics();


    /**
     * Обновляет существующую задачу.
     *
     * @param task задача с обновленными данными
     */


    void updateTask(Task task);


    /**
     * Обновляет существующую подзадачу.
     *
     * @param subtask подзадача с обновленными данными
     */


    void updateSubtask(Subtask subtask);


    /**
     * Обновляет существующий эпик.
     *
     * @param epic эпик с обновленными данными
     */


    void updateEpic(Epic epic);


    /**
     * Получает историю просмотров задач.
     *
     * @return список задач, которые были просмотрены
     */


    List<Task> getHistory();


}
