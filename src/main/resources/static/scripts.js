$(document).ready(function () {
    // Autovervollständigung für das Feld 'storageLocation' einrichten
    $('#storageLocation').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: 'https://lobid.org/gnd/search', // URL der API für die Autovervollständigung
                dataType: 'json',
                data: {
                    q: request.term, // Suchbegriff aus dem Eingabefeld
                    format: 'json:suggest'
                },
                success: function (data) {
                    if (data && Array.isArray(data)) {
                        // Verarbeitung der API-Antwort und Aufbau der Autovervollständigungsoptionen
                        response(data.map(item => ({
                            label: item.label,
                            value: item.label,
                            gndId: item.id
                        })));
                    } else {
                        console.error('Serverantwort enthält keine erwarteten Daten:', data)
                    }
                },
                error: function (xhr, status, error) {
                    console.error('Fehler beim Abrufen der Daten:', error);
                }
            });
        },
        minLength: 2, // Minimale Anzahl von Zeichen für die Autovervollständigung
        select: function (event, ui) {
            // Auswahl eines Eintrags und Setzen der Felder
            $('#storageLocation').val(ui.item.value);
            $('#storageLocationGndId').val(ui.item.gndId);
            return false; // Verhindert das Standardverhalten des Browsers
        }
    });

    // Autovervollständigung für das Feld 'owner' einrichten
    $('#owner').autocomplete({
        source: function (request, response) {
            $.ajax({
                url: 'https://lobid.org/gnd/search', // URL der API für die Autovervollständigung
                dataType: 'json',
                data: {
                    q: request.term, // Suchbegriff aus dem Eingabefeld
                    format: 'json:suggest'
                },
                success: function (data) {
                    if (data && Array.isArray(data)) {
                        // Verarbeitung der API-Antwort und Aufbau der Autovervollständigungsoptionen
                        response(data.map(item => ({
                            label: item.label,
                            value: item.label,
                            gndId: item.id
                        })));
                    } else {
                        console.error('Serverantwort enthält keine erwarteten Daten:', data)
                    }
                },
                error: function (xhr, status, error) {
                    console.error('Fehler beim Abrufen der Daten:', error)
                }
            });
        },
        minLength: 2, // Minimale Anzahl von Zeichen für die Autovervollständigung
        select: function (event, ui) {
            // Auswahl eines Eintrags und Setzen der Felder
            $('#owner').val(ui.item.value);
            $('#ownerGndId').val(ui.item.gndId);
            return false; // Verhindert das Standardverhalten des Browsers
        }
    });

    // Formularabsendung verhindern und stattdessen AJAX-Request senden
    $('#manuscriptForm').submit(function (event) {
        event.preventDefault(); // Verhindert das Standardformular-Absenden

        // Sammeln der Formulardaten
        var formData = {
            storageLocation: $('#storageLocation').val(),
            storageLocationGndId: $('#storageLocationGndId').val(),
            owner: $('#owner').val(),
            ownerGndId: $('#ownerGndId').val(),
            creationYear: $('#creationYear').val(),
            title: $('#title').val(),
            description: $('#description').val(),
            researcherEmail: $('#researcherEmail').val()
        };

        // AJAX-Request zum Absenden der Formulardaten
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/manuscripts', // URL des API-Endpunkts
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function (response) {
                alert('Manuskript erfolgreich registriert!');
                $('#manuscriptForm')[0].reset(); // Setzt das Formular zurück
            },
            error: function (error) {
                alert('Fehler bei der Registrierung des Manuskripts. Bitte versuchen Sie es erneut.');
                console.error('Fehler: ', error);
                if (error.responseText) {
                    console.error('Serverantwort:', error.responseText);
                }
            }
        });
    });
});