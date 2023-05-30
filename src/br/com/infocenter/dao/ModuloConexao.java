/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.infocenter.dao;

import java.sql.*; 

/**
 *
 * @author gustavo
 */
public class ModuloConexao {
    
   // método de conexão com o banco de dados
    public static Connection conector() {
        
        Connection conexao = null; 
        
        // a linha abaixo "chama" o driver 
        String driver = "com.mysql.cj.jdbc.Driver";
        
        // Armazenando informações do banco 
        String url = "jdbc:mysql://localhost:3306/infocenter"; 
        String user = "gustavo"; 
        String password="123"; 
        
        // Estabelecendo a conexão com o banco 
        
        try {
            
            Class.forName(driver); 
            conexao = DriverManager.getConnection(url, user,  password);
            return conexao; 
            
        } catch (Exception e) {
            
            // a linha abaixo serve de apoio para esclarecer o erro  
            
            //System.out.println(e);
            return null;
        }
    }
}
