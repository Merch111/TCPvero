/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpvero;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author elisa
 */
public class Server {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi() {
        try {
        System.out.println("SERVER IN ESECUZIONE");
        
        //creo un server sulla porta 6789
        server = new ServerSocket(6789);
        
        //rimane in attesa di un client
        client = server.accept();
        
        //chiudo il server
        server.close();
        
        //associo due oggetti al socket del client per effettuare la scrittura e la lettura
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server! ");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica() throws IOException {
            //rimango in attesa della riga trasmessa dal client
            System.out.println("benvenuto client, scrivi una frase e te la scrivo in maiuscolo! ");
            stringaRicevuta = inDalClient.readLine();
            System.out.println("ricevuta la stringa dal client: " + stringaRicevuta);
            
            //la modifico e la rispedisco al client
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("Invio la stringa modificata al client");
            outVersoClient.writeBytes(stringaModificata + "\n");
            
            //termina l'elaborazionesul server e chiudo la connessione con il client
            System.out.println("FINE ELABORAZIONE");
            client.close();
    }
}
