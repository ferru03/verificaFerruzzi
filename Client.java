package com.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket socket;
    int porta = 6789;
    String nomeServer = "localhost";
    //stream per comunicare
    DataOutputStream out;
    DataInputStream in;

    Scanner input = new Scanner(System.in);

    public Socket connetti(){
        try {
            System.out.println("Client avviato..");
            //creo il socket per connettermi al server
            socket = new Socket(nomeServer, porta);
            System.out.println("Client connesso con successo al server ' " + nomeServer + " ' sulla porta: " + porta);
            //inizializzo gli stream per consentire la comunicazione
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

        } catch (Exception e){
            System.out.println("Errore, impossibile connettersi");
            System.exit(1);
        }
        return socket;

    }

    //menu per scelta operazioni
    public void menu(){
        try{
            System.out.println("---Calcolatrice---");
            System.out.println("[+.Somma \t -.Differenza \t *.Moltiplicazione \t /.Divisione]");
            System.out.println("0.ESCI");
        } catch (Exception e){
            System.out.println("Errore");
        }


    }

    //funzione per comunicare
    public void comunica(){
        try {
            String scelta;
                menu();
                System.out.println("Inserire operazione ");
                scelta = input.nextLine();
                //invio la scelta al server
                //out.writeByte(scelta);
                out.writeBytes(scelta);

                //Inserisco i numeri e li spedisco al server
                System.out.println("Inserisci primo numero.");
                int x = input.nextInt();
                out.writeInt(x);

                System.out.println("Inserisci secondo numero.");
                int y = input.nextInt();
                out.writeInt(y);
        }catch (IOException e){
            System.out.println("Errore");
        }
    }
}
