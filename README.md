# Übungsprojekt: MySpace

MySpace ist ein Buchungserfassungssystem für Coworking Space, welches mit Quarkus entwickelt wird.

## Einrichtung des Projekts

Das Projekt MySpace basiert auf dem aktuellen Projekt 'Punchclock' von Modul 223 und wird überarbeitet.

Um dieses Projekt aufzusetzen, folge diesen Schritten:

1. Stelle sicher, dass du die folgenden Voraussetzungen erfüllst:
   - Java Development Kit (JDK) 11 oder höher
   - Apache Maven
   - Eine Datenbank (z. B. PostgreSQL) und die entsprechenden Verbindungsinformationen

2. Klone das Projekt von GitHub:

   git clone https://github.com/bomoba/mySpace.git

### Starten des Projekts

- Navigiere zum Hauptverzeichnis des Projekts in deiner Befehlszeile oder deinem Terminal.
-Starte die Anwendung mit dem integrierten Quarkus-Entwicklungsmodus.
-Die Anwendung sollte jetzt auf http://localhost:8080 verfügbar sein.

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.


#### Automatische Tests

1 Stelle sicher, dass die Anwendung läuft (siehe Abschnitt "Starten des Projekts").

2 Navigiere zum Hauptverzeichnis des Projekts in deiner Befehlszeile oder deinem Terminal.

3 Führe den Befehl aus, um Testdaten zu generieren:
mvn exec:java -Dexec.mainClass="ch.zli.m223.IntegrationTestDataService"

##### Bemerkung

Das Projekt war für mich sehr herausfordernd. Ich liess mich von Beispielen der Dozenten während des Unterrichts im Modul 223 inspirieren, sowie von anderem Code, den ich in Projekten für Module des ZLI und auch ausserhalb erstellt habe.

