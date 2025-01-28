import java.util.List;








public class Managers {





    public static TaskManager getDefault() {





        return new InMemoryTaskManager() {





            @Override





            public void deleteTask(int id) {





            }








            @Override





            public void deleteSubtask(int id) {








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





            public void updateTask(Task task) {





            }








            @Override





            public void updateSubtask(Subtask subtask) {








            }








            @Override





            public void updateEpic(Epic epic) {








            }





            // Здесь можно оставить реализацию по умолчанию





        };





    }








    public static HistoryManager getDefaultHistory() {





        return new InMemoryHistoryManager();





    }





}