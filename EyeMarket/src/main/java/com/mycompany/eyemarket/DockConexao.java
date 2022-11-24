/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eyemarket;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 *
 * @author duddaprado
 */
public class DockConexao {
  
    
    private JdbcTemplate connection;

    public  DockConexao () {      
        BasicDataSource dataSource = new BasicDataSource();


        dataSource​.setDriverClassName("com.mysql.cj.jdbc.Driver");

        dataSource​.setUrl("jdbc:mysql://localhost:3306/eyemarket");

        dataSource​.setUsername("root");

        dataSource​.setPassword("#Gfgrupo1");

        this.connection = new JdbcTemplate(dataSource);
        
    }
    
    public JdbcTemplate getConnection() {

        return connection;

    }
    
    
    public static void main(String[] args) {
        Looca looca = new Looca();
        Sistema sistema = looca.getSistema();
        System.out.println(sistema);
    }
    
    
    
}
