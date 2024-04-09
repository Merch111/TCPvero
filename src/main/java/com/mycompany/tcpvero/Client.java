/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpvero;

import java.io.*;
import java.net.*;
/**
 *
 * @author elisa
 */
public class Client {
    String nomeServer = "nomeServer";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    protected Socket connetti(){
        System.out.println("CLIENT IN ESECUZIONE");
        try {
            //per l'input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            
            //creo un socket
            mioSocket = new Socket(nomeServer, portaServer);
            
            //associo due oggetti al socket per effettuare la scrittura e la lettura
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return mioSocket;
    }
    
    public void comunica() throws IOException {
        try{
        //leggo una riga
        System.out.println("inserisci la stringa da inviare al server: " + '\n');
        stringaUtente = tastiera.readLine();
        
        //la spedisco al server
        System.out.println("Invio la stringa al server e attendo!");
        outVersoServer.writeBytes(stringaUtente + "\n");
        
        //leggo la risposta dal server
        stringaRicevutaDalServer = inDalServer.readLine();
        System.out.println("Risposta dal server: " + "\n" + stringaRicevutaDalServer);
        
        //chiudo la connessione
        System.out.println("Termina elaborazione e viene chiusa la connessione");
        mioSocket.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il server");
            System.exit(1);
        } 
    }
    
}
