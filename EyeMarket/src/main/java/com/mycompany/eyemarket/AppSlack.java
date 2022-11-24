/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eyemarket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author duddaprado
 */
public class AppSlack {
    
     public static void main(String[] args) throws IOException, InterruptedException {
       JSONObject json = new JSONObject();
       
       json.put("text", "TESTE2 :shrug:");
       
       Slack.sendMessage(json);
    }
    
}
