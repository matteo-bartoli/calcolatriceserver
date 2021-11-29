package com.bartoli;

import java.io.IOException;

public class Calcolatrice {
    
    char segno;
    int num1 = 0;
    int num2 = 0;
    int risultato;
    Server server;

    public Calcolatrice(String num1, String num2, String segno) {
        this.num1 = toInt(num1);
        this.num2 = toInt(num2);
        this.segno = toChar(segno);
        server = new Server();
    }

    

    public char toChar(String val){
        return val.charAt(0);
    }
    
    public int toInt(String val){
        return Integer.parseInt(val);
    }

    public int somma(int num1, int num2) {
        return num1 + num2;
    }
    
    public int moltiplicazione(int num1, int num2) {
        return num1 * num2;
    }
   
    public int sottrazione(int num1, int num2) {
        return num1 - num2;
    }

    public int divisione(int num1, int num2) {
        return num1 / num2;
    }

    public void operazione() {
        switch (segno) {
            case '+':
                risultato = somma(num1, num2);
                break;
            case '-':
                risultato = sottrazione(num1, num2);
                break;
            case '*':
                risultato = moltiplicazione(num1, num2);
                break;
            case '/':
                risultato = divisione(num1, num2);
                break;
            default:
                try {
                    server.outVersoClient.writeBytes("S -> Non capisco il segno\n");
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
        }
        try {
            server.outVersoClient.writeBytes("S -> Il risultato è " + risultato +"\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

