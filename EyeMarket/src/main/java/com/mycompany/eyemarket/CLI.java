/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eyemarket;

import com.github.britooo.looca.api.core.Looca;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Mario Sergio
 */
public class CLI {

    public static void main(String[] args) {
        Scanner leitorId = new Scanner(System.in);
        Scanner leitorStr = new Scanner(System.in);
        Conexao connect = new Conexao();
        JdbcTemplate banco = connect.getConnection();
        Boolean isExisteTotem = false;
        Looca looca = new Looca();
        String processador = looca.getProcessador().getNome();
        String sistemaOperacional = looca.getSistema().getSistemaOperacional();
        
        //ID MAQUINA
        System.out.println("\n\nBem vindo ao programa de monitoramento Eye Market!"
                + "\n\nDigite o ID da Maquina: ");
        Integer idMaquina = leitorId.nextInt();

        List<Totem> listaTotem = banco.query("SELECT idTotem,processador,sistemaOperacional,dataInstalacao,isLigado FROM totem",
                new BeanPropertyRowMapper<>(Totem.class));

        for (Totem totem : listaTotem) {
            if (totem.getIdTotem() == idMaquina) {
                banco.execute(
                        String.format("UPDATE totem SET isLigado = 1 WHERE idTotem = %d", idMaquina)
                );
                System.out.println("\nEsse totem ja e cadastrado!");
                isExisteTotem = true;
            }
        }

        if (!isExisteTotem) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dataAtual = dateFormat.format(date);
            System.out.println("\nId Cadastrato com sucesso!");
            banco.execute(
                    String.format("INSERT INTO Totem VALUES(%d,'%s','%s','%s',1, 'normal');", idMaquina, processador, sistemaOperacional, dataAtual)
            );
        }

        //LOGIN USUARIO
        Boolean isExiste = false;

        do {
            System.out.println("\nDigite seu email: ");
            String login = leitorStr.nextLine();

            System.out.println("\nDigite sua senha: ");
            String senha = leitorStr.nextLine();

            List<Usuario> listaUsuarios = banco.query("SELECT * FROM Usuario",
                    new BeanPropertyRowMapper<>(Usuario.class));

            if (login.isEmpty() || senha.isEmpty()) {
                System.out.println("\nOs campos não podem ficar vazios!");
            }

            for (Usuario usuario : listaUsuarios) {
                if (usuario.getEmail().equals(login) && usuario.getSenha().equals(senha)) {
                    isExiste = true;
                }
            }

            if (isExiste) {
                System.out.println("\nLOGADO");
            } else {
                System.out.println("\nEmail ou senha incorretos");
            }
        } while (!isExiste);

        //INSERT DADOS DA MAQUINA
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String dataAtual = dateFormat.format(date);

                long memorialong = looca.getMemoria().getEmUso();
                String memoria = Long.toString(memorialong);

                long memoriaDisponivellong = looca.getMemoria().getDisponivel();
                String memoriaDisponivel = Long.toString(memoriaDisponivellong);

                long grupprocessolong = looca.getGrupoDeProcessos().getTotalProcessos();
                String GrupoDeProcessos = Long.toString(grupprocessolong);

                int i = looca.getGrupoDeProcessos().getTotalProcessos();
                String processo = String.valueOf(i);

                Date ax = Date.from(looca.getSistema().getInicializado());
                String sistema = String.valueOf(ax);

                String tempoAtividade = "" + looca.getSistema().getTempoDeAtividade();

                banco.update(String.format("INSERT INTO DadosTotem VALUES('%s',%s,%s,%s,%s,%d);",
                        dataAtual, memoria, memoriaDisponivel, processo, tempoAtividade, idMaquina));
                
                System.out.println(String.format("\nDados inseridos com sucesso: "
                        + "\nMemoria utilizada: %s"
                        + "\nMemoria disponivel: %s"
                        + "\nQuantidade de processos ativos: %s"
                        + "\nTempo de atividade: %s"
                        , memoria, memoriaDisponivel, processo, tempoAtividade));
            }
        }, 0, 5000);
    }
}
