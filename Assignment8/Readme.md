**Aufgabe 8 zu Verteilte Systeme Prof.Dr.E.Ammann**

Schreiben Sie eine Web Service-basierte Lösung zur Umsetzung des Produzenten/Konsumentenproblems.

Der serverseitige Teil läuft auf einer Web Service-Plattform. Im wesentlich bieten Sie zwei Webservice-Methoden an: einfuegen und auslesen. Ihr Serverteil verwaltet einen Ringpuffer, in den Produzenten- und Konsumenten-Klientenprogramme ihre Produkte durch Aufruf dieser beiden Web Services einstellen / entnehmen können.

Die Klientenprogramme (Produzenten und Konsumenten in beliebiger Zahl) erteilen ihre Aufträge an den Server über das Netzwerk per Web Service-Aufruf.

**Hinweis:**

Falls Sie als Web Service Plattform die in der Vorlesung vorgestellte, durch die ab JDK 1.6 Standard Edition bereitgestellte Lösung wählen, beachten Sie bitte folgendes:

Die Service-Seite, d.h. der Endpoint Publisher, stellt in der in der Vorlesung vorgestellten Form eine single-threaded Lösung dar. Das bedeutet, dass z.B ein Produzentenaufruf nicht warten darf, falls gerade kein Platz im Puffer zum Einfügen vorhanden ist. Sonst wären nämlich auch alle weiteren Produzenten- und Konsumentenaufrufe damit blockiert. Entsprechendes gilt für Konsumentenaufrufe.

Sie können also insbesondere die in der Vorlesung diskutierte FIFOBuffer-Klasse hier nicht unverändert einsetzen.


Die Abnahme des Programms erfolgt nach Absprache mit mir, zur Vorführung des lauffähigen Programms sollten mehrere Rechner eingesetzt werden. 