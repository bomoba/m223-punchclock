# Übungsprojekt: MySpace

MySpace ist ein Buchungserfassungssystem für Coworking Space, welches mit Quarkus entwickelt wird.

## Einrichtung des Projekts

Projekt MySpace basiert auf dem aktuellen Projekt, das bearbeitet wird.
Um dieses Projekt aufzusetzen, folge diesen Schritten:

1. Stelle sicher, dass du die folgenden Voraussetzungen erfüllst:
   - Java Development Kit (JDK) 11 oder höher
   - Apache Maven
   - Eine Datenbank (z. B. PostgreSQL) und die entsprechenden Verbindungsinformationen

2. Klone das Projekt von GitHub:
   ```bash
   git clone https://github.com/dein-benutzername/mein-projekt.git

### Starten des Projekts

- Navigiere zum Hauptverzeichnis des Projekts in deiner Befehlszeile oder deinem Terminal.
-Starte die Anwendung mit dem integrierten Quarkus-Entwicklungsmodus.
-Die Anwendung sollte jetzt auf http://localhost:8080 verfügbar sein.

Die Daten werden in einer PostgreSQL-Datenbank gespeichert. In der Entwicklungsumgebung wird diese in der [docker-compose-yml](./.devcontainer/docker-compose.yml) konfiguriert.

#### Testdaten

Über http://localhost:5050 ist PgAdmin4 erreichbar. Damit lässt sich die Datenbank komfortabel verwalten. Der Benutzername lautet `zli@example.com` und das Passwort `zli*123`. Die Verbindung zur PostgreSQL-Datenbank muss zuerst mit folgenden Daten konfiguriert werden:
 - Host name/address: `db`
 - Port: `5432`
 - Maintenance database: `postgres`
 - Username: `postgres`
 - Password: `postgres`

## Automatische Tests

1 Stelle sicher, dass die Anwendung läuft (siehe Abschnitt "Starten des Projekts").

2 Navigiere zum Hauptverzeichnis des Projekts in deiner Befehlszeile oder deinem Terminal.

3 Führe den Befehl aus, um Testdaten zu generieren:
mvn exec:java -Dexec.mainClass="ch.zli.m223.IntegrationTestDataService"



