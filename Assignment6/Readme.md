> Schreiben Sie ein verteiltes Programm, das das Problem der byzantinischen Generäle
> löst. Beschränken Sie sich auf den Fall von 4 Generälen, von denen höchstens einer
> sich „byzantinisch“ (d.h. betrügerisch) verhält, d.h n=4 und f=1.

> Implementieren Sie die klassische Form des Problems, in dem ein ausgezeichneter
> General einen Wert vorschlägt und alle einen Konsens über die Wert gewinnen sollen
> (natürlich kann dieser ausgezeichnete General selber der „betrügerische“ sein).
> Das grundsätzliche Problem und das Lösungsverfahren werden in der Vorlesung
> besprochen.

> Ihre Programmlösung sollte folgende typische Szenarien zeigen können:

> • Alle Generäle arbeiten korrekt.

> • Ein General (nicht der Kommander) verhält sich fehlerhaft und schickt in
> der zweiten Runde des Verfahrens verschiedene Werte an die anderen
> Generäle.

> • Ein General (nicht der Kommander) verhält sich fehlerhaft und schickt in
> der zweiten Runde des Verfahrens den korrekten Wert an einen der
> anderen Generäle und keinen Wert an den anderen General.

> • Der Kommander verhält sich fehlerhaft und schickt in der ersten Runde
> den gleichen Wert an zwei Generäle und einen anderen Wert an den
> dritten General.

> • Der Kommander verhält sich fehlerhaft und schickt in der ersten Runde
> einen Wert (gleich oder verschieden) an zwei Generäle und keinen Wert
> an den dritten General.

> Mögliche Implementierungsart: per Messaging mit JMS.

> Falls Sie diese nutzen wollen, steht Ihnen ein JMS Provider (auf
> infpro52.reutlingen-university.de) als Nachrichtenvermittler zur Verfügung.
> Je nach Ihrer Implementierungsidee sind dort bereits Queues (aufgabe6Queue1, …,
> aufgabe6Queue4) und Topics (aufgabe6Topic1, …, aufgabe6Topic4) für Sie angelegt.

> Die Abnahme des Programms erfolgt nach Absprache mit mir.