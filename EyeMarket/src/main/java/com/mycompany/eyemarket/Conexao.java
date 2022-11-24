package com.mycompany.eyemarket;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.sistema.Sistema;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate connection;

    public Conexao() {      
        BasicDataSource dataSource = new BasicDataSource();

        dataSource​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        dataSource​.setUrl("jdbc:sqlserver://eyemarketbd.database.windows.net:1433;database=eyemarketbd;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;");

        dataSource​.setUsername("admin-eyemarket");

        dataSource​.setPassword("#Gfgrupo1");

        this.connection = new JdbcTemplate(dataSource);

//       


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
