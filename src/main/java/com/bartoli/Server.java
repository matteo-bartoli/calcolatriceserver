package com.bartoli;
import java.io.*;
import java.net.*;

public class Server {
    ServerSocket server = null;
    Socket client = null;
    String segno = null;
    String num1 = null;
    String num2 = null;
    String azione = null;
    String Risultato = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    Calcolatrice calcolatrice = null;

    public Socket attendi(){
        try{
            System.out.println("S -> Esecuzione server");

            server = new ServerSocket(6789);

            client = server.accept();

            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore del server!");
            System.exit(1);
        }
        return client;
    }

    public void comunica(){
        try {
            System.out.println("Ciao");
            do{
            outVersoClient.writeBytes("S -> Inserisci il 1 valore\n");
            num1 = inDalClient.readLine();
            outVersoClient.writeBytes("S -> Inserisci il 2 valore\n");
            num2 = inDalClient.readLine();
            outVersoClient.writeBytes("S -> Inserisci il segno (+,-,*,/)\n");
            segno = inDalClient.readLine();
            calcolatrice = new Calcolatrice(num1, num2, segno);
            outVersoClient.writeBytes("S -> Servo ancora?\n");
            azione = inDalClient.readLine();
            }while(azione.equalsIgnoreCase("Y"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void close(){
        try {
            inDalClient.close();
            outVersoClient.close();
            client.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}               

