package Client;

import Server.OurServer;

/**
 * Unter Nutzung des Cliententeil können beliebig viele Nutzer sowohl ihre Meinung zu
 * diesem Thema kundtun (ja, nein, Enthaltung) als auch den aktuellen Stand der Abstimmung vom Server erfragen.
 * Created by jm on 10/10/2014.
 */
public class Client {


    public static void main(String[] args) {
        OurServer sr = new OurServer();
        sr.currStatus();
    }



}
