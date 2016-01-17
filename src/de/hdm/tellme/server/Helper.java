package de.hdm.tellme.server;

/**
 * Die Klasse <class> Helper </class> ist ein Logger, um in dem
 * Konsolenfenster bestimmte Verhaltensweisen abzubilden, der die aufgerufenen
 * Methoden mitloggt
 *
 * @author denispokorski
 *
 */
public class Helper {

	/**
	 * Anleitung: 
	 * 
	 * 
	 */
	
	
	public static boolean debugModus = true;

	// ANLEITUNG: LOGGER
	// EINFACH STATISCHE METHODE AUFRUFEN, BITTE NAHCRICHT MIT METHODENNAMEN
	// BEGINNEN
	// BEISPIEL:
	// Helper.LogWarning("getAlleSichtbarenUnterhaltungenFuerTeilnehmer - sichtbare Unterhaltung ohne Sichtbare Nachricht entdeckt. UnterhaltungsID: "
	// + unterhaltung.getId());

	
	public static void LogInformation(String _nachricht) {
		Helper.Log("INFO - " + _nachricht);
	}

	public static void LogDebug(String _nachricht) {
		if (debugModus) {
			Helper.Log("DEBUG - " + _nachricht);
		}
	}

	public static void LogWarnung(String _nachricht) {
		Helper.Log("WARNUNG - " + _nachricht);

	}

	public static void LogFehler(String _nachricht) {
		Helper.Log("FEHLER - " + _nachricht);

	}

	public static void LogAusnahme(Exception ex) {
		Helper.Log("FEHLER - " + "ExceptionMessage: " + ex);
		ex.printStackTrace();

		StackTraceElement[] stackTraceElements = ex.getStackTrace();
		if (stackTraceElements != null) {
			for (StackTraceElement stackTraceElement : stackTraceElements) {
				Helper.Log("\t" + stackTraceElement.toString());
			}

		}

	}

	private static void Log(String _nachricht) {
		System.out.println(_nachricht);

	}

}
