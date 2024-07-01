Handschriftenportal Projekt

Dieses Projekt ist eine Webanwendung zur Registrierung und Verwaltung von Handschriften. Es verwendet Spring Boot für das Backend, PostgreSQL für die Datenbank und jQuery für die Frontend-Interaktionen. Die Anwendung ist mit Docker containerisiert.

Inhaltsverzeichnis

	•	Funktionen
	•	Verwendete Technologien
	•	Erste Schritte
	•	Voraussetzungen
	•	Installation
	•	Verwendung
	•	API-Endpunkte

Funktionen

	•	Registrierung neuer Handschriften mit verschiedenen Details.
	•	Abrufen einer Liste aller registrierten Handschriften.
	•	Autovervollständigungsfunktion für bestimmte Felder mit einer externen API.

Verwendete Technologien

	•	Java
	•	Spring Boot
	•	JPA/Hibernate
	•	PostgreSQL
	•	jQuery
	•	Docker

Erste Schritte

Voraussetzungen

	•	Docker
	•	Docker Compose

Installation

  1.	Klonen Sie das Repository:

git clone https://github.com/yourusername/handschriftenportal.git
cd handschriftenportal

  2.	Bauen und starten Sie die Docker-Container:

docker-compose up --build


Verwendung

	1.	Registrieren Sie eine Handschrift:
Füllen Sie das Formular auf der Hauptseite aus und klicken Sie auf “Registrieren”.
	2.	Anzeigen registrierter Handschriften:
Navigieren Sie zum Endpoint /api/manuscripts, um alle Handschriften im JSON-Format anzuzeigen.

API-Endpunkte

	•	POST /api/manuscripts
Registrierung einer neuen Handschrift.
Request Body:

{
  "storageLocation": "string",
  "storageLocationGndId": "string",
  "owner": "string",
  "ownerGndId": "string",
  "creationYear": 1400,
  "title": "string",
  "description": "string",
  "researcherEmail": "string"
}

Response:
	•	201 Created: Handschrift erfolgreich registriert.
	•	500 Internal Server Error: Fehler bei der Registrierung der Handschrift.

	•	GET /api/manuscripts
Abrufen einer Liste aller Handschriften.
Response:
	•	200 OK: Liste der Handschriften.

Detaillierte Dateiübersicht

Backend

	•	Controller: Verwalten der API-Endpunkte (ManuscriptController).
	•	Entity: Repräsentiert das Manuskript-Modell (Manuscript).
	•	Repository: Verwalten der Datenbankoperationen (ManuscriptRepository).
	•	Service: Enthält die Geschäftslogik (ManuscriptService und ManuscriptServiceImpl).

Frontend

	•	index.html: Haupt-HTML-Datei mit Formular und UI-Elementen.
	•	scripts.js: Enthält jQuery für die Formularübermittlung und Autovervollständigung.
	•	style.css: Stile für die HTML-Seite.

Docker-Konfiguration

Details zur Docker-Konfiguration finden Sie in den Dateien Dockerfile und docker-compose.yml im Repository.
