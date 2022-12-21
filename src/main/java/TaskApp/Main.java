package TaskApp;
import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

public class Main {

  
    public static void main(String[] args) {
  
       TaskController taskController = new TaskController();
       
       Task task = new Task();
       
       task.setIdProject(3);
       task.setName("Criar as telas da aplicação");
       task.setDescription("Devem ser criadas telas para os cadastros");
       task.setNotes("Sem notas");
       task.setIsCompleted(false);
       task.setDeadline(new Date());
       
       taskController.save(task);
       
      
       
       
      
     
      
      
      
     
    }
    
}
