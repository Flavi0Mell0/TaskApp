/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Flávio
 */
public class ProjectController {
    
    
    public void save(Project project){
        
      String sql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";
      
      Connection connection = null;
      PreparedStatement statement = null;
      
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
           
        } catch (SQLException ex) {
            
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            
            ConnectionFactory.closeConnection(connection, statement);
        }

    }
    public void update(Project project) {
        
        String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, project.getId());
            statement.setString(2, project.getName());
            statement.setString(3,project.getDescription());
            statement.setDate(4, new Date(project.getCreatedAt().getTime()));
            statement.setDate(5, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto", ex);
           
         
        } 
        
    }
        
     public List<Project> getAll(){
         
         String sql = "SELECT * FROM projects";
         
         List<Project> projects = new ArrayList<>();
         
         Connection connection = null;
         PreparedStatement statement = null;
         
         // classe que vai recuperar os dados no banco de dados
         
         ResultSet resultSet = null;
         
         try {
             connection = ConnectionFactory.getConnection();
             statement = connection.prepareStatement(sql);
             
             resultSet = statement.executeQuery();
             
             // enquanto existir dados no banco de dados, faça
             
             while (resultSet.next()) {
                 
                 Project project = new Project();
                 
                 project.setId(resultSet.getInt("id"));
                 project.setName(resultSet.getString("name"));
                 project.setDescription(resultSet.getString("description"));
                 project.setCreatedAt(resultSet.getDate("createdAt"));
                 project.setUpdatedAt(resultSet.getDate("updatedAt"));
                 
                 //Adiciono o contato recuperado à lista de contatos
                 
                 projects.add(project);

             }
         } catch (SQLException ex) {
             throw new RuntimeException("Erro ao buscar projetos", ex);
            
         } finally {
             ConnectionFactory.closeConnection(connection, statement, resultSet);
           
         }
         
         return projects;
         
     }   
    
    
     public void removeById(int id){
       
       String sql = "DELETE FROM projects WHERE id = ?";
       
       Connection connection = null;
       PreparedStatement statement = null;
       
       try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           statement.setInt(1, id);
           statement.execute();
           
       } catch (SQLException ex) {
           throw new RuntimeException("Erro ao deletar projeto", ex);
       } finally {
           
           ConnectionFactory.closeConnection(connection, statement);
       }
       
       
   }
}
