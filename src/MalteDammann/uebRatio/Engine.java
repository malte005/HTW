package MalteDammann.uebRatio;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Beschreibung:
 * Engine-Klasse
 *
 * @author Malte Dammann
 * E-Mail: s0549309@htw-berlin.de
 * Bearbeitungszeitraum: 29.11.14 - 18.01.2015
 *
 * Modul: Preogrammierung 1
 * Dateiname: Engine.Java
 *
 * @version Produkt: NetBeans IDE 8.0.2, Java: 1.8.0_20; Java HotSpot(TM) 64-Bit, Server VM 25.20-b23
 *
 * @since 2014-12-12
 *
 */
public class Engine {

    // Klassenvariablen
    private static Ratio ratio1, ratio2, ergebnis;
    private static final ArrayList<String> OPERATOREN = new ArrayList<>();
    private static final ArrayList<String> RATIO_ZAHLEN = new ArrayList<>();

    /**
     * losgehts-Methode:
     * Hauptmethode, welche die Interaktion mit dem Benutzer über die Konsole
     * handelt.
     */
    public void losgehts() {

        // Attribute
        Scanner scanner = new Scanner(System.in);
        String pattern = "^(\\+|-)?[0-9]*[//][0-9]*";   // Eingabepattern für: (+/-)Zähler / Nenner
        String stringRatio1;
        String stringRatio2;
        String opperator;
        boolean eingabeFehler;
        boolean weiter;
        String[] splitResult;

        System.out.println("Willkommen beim Ratio-Rechner =)");

        // Schleife für mehrere Rechnungen
        do {
            System.out.println("Bitte rationale Zahl in der Form '(+-)z/n' eingeben:");

            // Schleife für Eingabefehler des 1. Ratio-Strings
            do {
                // Benutzereingabe
                stringRatio1 = scanner.next();
                
                // Check Division durch 0 der 1. rationalen Zahl
                splitResult = stringRatio1.split("/");
                int n = Integer.parseInt(splitResult[1]);

                 if (n == 0) {
                    System.err.println("Division durch '0' ist nicht erlaubt. Erneut die zweite rationale Zahl eingeben...");
                    eingabeFehler = true;
                } else if (stringRatio1.matches(pattern)) {
                    eingabeFehler = false;
                } else {
                    System.err.println("Bitte eine rationale Zahl in genau der Form '(+-)z/n' eingeben...");
                    eingabeFehler = true;
                }

            } while (eingabeFehler);

            System.out.println("Danke! Jetzt bitte die zweite rationale Zahl in der gleich Form eingben '(+-)z/n' :");

            // Schleife für Eingabefehler des 2. Ratio-Strings
            do {
                stringRatio2 = scanner.next();

                // Check Division durch 0 der 2. rationalen Zahl
                splitResult = stringRatio2.split("/");
                int n = Integer.parseInt(splitResult[1]);

                if (n == 0) {
                    System.err.println("Division durch '0' ist nicht erlaubt. Erneut die zweite rationale Zahl eingeben...");
                    eingabeFehler = true;
                } else if (stringRatio2.matches(pattern)) {
                    eingabeFehler = false;
                } else {
                    System.err.println("Bitte eine rationale Zahl in genau der Form '(+-)z/n' eingeben...");
                    eingabeFehler = true;
                }

            } while (eingabeFehler);

            // Beide Ratio-Objekte werden aus den korrekten Strings erstellt
            ratio1 = string2ratio(stringRatio1);
            ratio2 = string2ratio(stringRatio2);
            
            System.out.println("Bitte jetzt eienen Operator eingeben [+-*/]:");

            // Schleife für Eingabefehler der Rechenoperation
            do {
                opperator = scanner.next();

                if (opperator.equals("+") || opperator.equals("-") || opperator.equals("*") || opperator.equals("/")) {
                    eingabeFehler = false;
                } else {
                    System.err.println("Bitte einen gültigen Operator [+-*/] eingeben...");
                    eingabeFehler = true;
                }

            } while (eingabeFehler);

            // Abspeichern der Ratio-Objekte und des Operators im Speicher
            saveRatioZahl(ratio1);
            saveRatioZahl(ratio2);
            saveOperator(opperator);

            // Rechenoperation wird in der aufgerufenen Methode - rechen() - aufgerufen
            rechnen(ratio1, ratio2, opperator);

            // Abspeichern des Ergebnis-Objekts im Speicher
            saveRatioZahl(ergebnis);

            // Ausgabe Ergebnis
            System.out.println("Ergebnis: " + ergebnis.toString());

            System.out.println("Weiter rechnen? [j/n]");

            // Abfrage und gleichzeitige Zuweisung, ob weiter gerechnet werden soll
            weiter = scanner.next().charAt(0) != 'n';

        } while (weiter);

        // Ausgabe der Rechenergebnisse
        ergebnisAusgabe();

    }// end of losgehts

    /**
     * ergebnisAusgabe-Methode:
     * Gibt alle vorgenommenen Rechnungen mit Ergebnis als String aus
     *
     */
    private void ergebnisAusgabe() {

        int anzahlRechnungen = RATIO_ZAHLEN.size() / 3;
        int temp = 0;

        System.out.println("\n---- Berechnungen ----");

        // Speicher auslesen und in der Schleife ausgeben
        for (int i = 1; i <= anzahlRechnungen; i++) {
            System.out.println("(" + restore(temp) + ") " + restoreOpperator(temp / 3) + " (" + restore(temp + 1) + ") = " + restore(temp + 2));
            temp += 3;
        }

        // Speicher wieder leeren
        clearMemory();

    }// end of ergebnisAusgabe

    /**
     * rechne-Methode:
     * entscheidet welche Rechen-Methode aufgerufen werden soll
     *
     * @param ratio1 (Ratio): erster Rochenoperant
     * @param ratio1 (Ratio): zweiter Rochenoperant
     * @param op     (String): Rechenoperator
     */
    private void rechnen(Ratio ratio1, Ratio ratio2, String op) {

        switch (op) {
            case "+":
                ergebnis = ratio1.addiere(ratio2);
                break;
            case "-":
                ergebnis = ratio1.subtrahiere(ratio2);
                break;
            case "*":
                ergebnis = ratio1.multipliziere(ratio2);
                break;
            case "/":
                ergebnis = ratio1.dividiere(ratio2);
                break;
        }
    }// end of rechnen

    /**
     * saveRatioZahl-Methode:
     * sichert die rationale Zahl in Stringform in den Speicher (Array)
     *
     * @param ratio (Ratio): rationale Zahl
     */
    public void saveRatioZahl(Ratio ratio) {

        RATIO_ZAHLEN.add(String.valueOf(ratio));
    }// end of saveRatioZahl

    /**
     * saveOperator-Methode:
     * sichert den Operator als String in den Speicher (Array)
     *
     * @param op (String): Rechenoparator
     */
    public void saveOperator(String op) {

        OPERATOREN.add(op);
    }// end of saveOperator

    /**
     * restore-Methode:
     * gibt einen String aus dem Speicher zurück
     *
     * @param index (int): die Position des String im Speicher (Array)
     *
     * @return value (String): Rechenoperator
     */
    public String restoreOpperator(int index) {

        String value = OPERATOREN.get(index);
        return value;
    }

    /**
     * restore-Methode:
     * gibt einen String mit Ratio-Objekten aus dem Speicher zurück
     *
     * @param index (int): die Position des String im Speicher (Array)
     *
     * @return value (String): Ratio-Objekt
     */
    public String restore(int index) {

        String value = RATIO_ZAHLEN.get(index);
        return value;
    }// end of restore

    /**
     * string2ratio-Methode:
     * konvertiert einen String in eine rationale Zahl (Ratio-Objekt)
     *
     * @param str - enthält die rationale Zahl in Stringform "2/3"
     *
     * @return Ratio - die Zahl als rationale Zahl
     */
    private Ratio string2ratio(String strRatio) {

        String[] splitResult = strRatio.split("/");
        int z = 1, n = 1;

        try {
            z = Integer.parseInt(splitResult[0]);
            n = Integer.parseInt(splitResult[1]);

        } catch (NumberFormatException e) {
            System.err.println("Eingegebene Zahl war zu hoch für den Datentype Integer... Es wird mit 1 weitergerechenet.");
        }

        Ratio ratio = new Ratio(z, n);

        return ratio;
    }// end of string2ratio

    /**
     * clearMemory-Methode:
     * leer das Array mit den Rechenoptionen und das mit den Ratio-Objekten
     */
    private void clearMemory() {

        RATIO_ZAHLEN.clear();
        OPERATOREN.clear();
        
        System.out.println("\nSpeicher bereinigt.\n");
    }// end of clearMemory

}// end of class
