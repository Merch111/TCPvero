/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpvero;

/**
 *
 * @author elisa
 */
public class MainServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server servente = new Server();
        servente.attendi();
        servente.comunica();
    }
    
}
