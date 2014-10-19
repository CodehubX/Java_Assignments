package Server;

import Client.Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Unter Nutzung des Cliententeil können beliebig viele Nutzer sowohl ihre Meinung zu
 * diesem Thema kundtun (ja, nein, Enthaltung) als auch den aktuellen Stand der Abstimmung vom Server erfragen.
 * Created by jm on 10/10/2014.
 */
public class HandleRequests implements Runnable {
    int port= Main.port;
    ExecutorService ex;
    ServerSocket soc;

    public HandleRequests() throws IOException {
        this.port = OurServer.port;
        this.ex = Executors.newFixedThreadPool(10);
        this.soc = new ServerSocket(port);

    }

    @Override public void run() {
        try {
            //Endlos-Schleife: warte auf Client-Anforderungen
            while (true) {
        /*
         Zunächst wird eine Client-Anforderung entgegengenommen(accept-Methode).
         Der ExecutorService pool liefert einen Thread, dessen run-Methode
         durch die run-Methode der Handler-Instanz realisiert wird.
         Dem Handler werden als Parameter übergeben:
         der ServerSocket und der Socket des anfordernden Clients.
        */
                Socket cs = soc.accept();  //warten auf Client-Anforderung

                //starte den Handler-Thread zur Realisierung der Client-Anforderung
                //ex.execute(new Client(soc, cs));
            }
        } catch (IOException ex) {
            System.out.println("--- Interrupt NetworkService-run");
        }
    }
}
