package MalteDammann;

/**
 * Beschreibung:
 * Start des Programms (main) für die bewertete Abgabe: Rationale Zahlen
 *
 * @author Malte Dammann
 * E-Mail: s0549309@htw-berlin.de
 * Bearbeitungszeitraum: 29.11.14 - 18.01.2015
 *
 * Modul: Preogrammierung 1
 * Dateiname: Start.Java
 *
 * @version Produkt: NetBeans IDE 8.0.1, Java: 1.8.0_20; Java HotSpot(TM) 64-Bit, Server VM 25.20-b23
 *
 * @since 2014-11-29
 *
 */

import MalteDammann.uebRatio.Engine;

public class Start {

    /**
     * @param args
     * 
     * main-Methode:
     * Methode zum Einstieg in das Programm.
     * Engine-Objekt wird erstellt und dessen Methode "losgehts" aufgerufen.
     */
	public static void main(String[] args) {
		Engine rm = new Engine();
		rm.losgehts();            
            
	}// end of main
}// end of class
