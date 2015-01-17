package MalteDammann.uebRatio;

/**
 * Beschreibung:
 * Interface für die Klasse Ratio, um die unterschiedlichen Rechenoperationen
 * zu implementieren.
 *
 * @author Malte Dammann
 * E-Mail: s0549309@htw-berlin.de
 * Bearbeitungszeitraum: 29.11.14 - 18.01.2015
 *
 * Modul: Programmierung 1
 * 
 * Dateiname: IRatio.java
 * IDE: NetBeans IDE 8.0.2
 * Java: 1.8.0_20; Java HotSpot(TM) 64-Bit
 *
 * @since 2014-11-29
 *
 */

public interface IRatio {
	
	public Ratio addiere(Ratio zahl2);

	public Ratio subtrahiere(Ratio zahl2);

	public Ratio multipliziere(Ratio zahl2);

	public Ratio dividiere(Ratio zahl2) throws ArithmeticException;
	
	public Ratio kuerze(); // Euklidischer Algorithmus 

}// end of interface
