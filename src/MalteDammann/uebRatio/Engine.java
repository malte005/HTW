package MalteDammann.uebRatio;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Beschreibung:
 * Engine-Klasse handelt die Interaktion mit dem User. Stellt das Herzdtück des
 * Ratio-Rechners dar.
 *
 * @author Malte Dammann
 * E-Mail: s0549309@htw-berlin.de
 * Bearbeitungszeitraum: 29.11.14 - 18.01.2015
 *
 * Modul: Programmierung 1
 * 
 * Dateiname: Engine.java
 * IDE: NetBeans IDE 8.0.2
 * Java: 1.8.0_20; Java HotSpot(TM) 64-Bit
 *
 * @since 2015-01-02
 *
 */
public class Engine {

    // Klassenvariablen
    private static Ratio ratio1, ratio2, ergebnisRatio;
    private static String operator;
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
        String pattern = "^(\\+|-)?[0-9][0-9]*[//][1-9][0-9]*";   // Eingabepattern für: "(+/-)Zähler / Nenner"
        String stringRatio1;
        String stringRatio2;
        
        boolean eingabeFehler;
        boolean weiter;

        System.out.println("Willkommen beim Ratio-Rechner =)");

        // Schleife für mehrere Rechnungen hintereinander
        do {
            System.out.println("Bitte rationale Zahl in der Form '(+-)z/n' eingeben:");

            // Schleife für Eingabefehler des 1. Ratio-Strings
            do {
                // Benutzereingabe
                stringRatio1 = scanner.next();

                if (stringRatio1.matches(pattern)) {
                    eingabeFehler = false;
                } else {
                    System.err.println("Bitte eine rationale Zahl in genau der Form '(+-)z/n' eingeben und die mathematischen Gesetze beachten...");
                    eingabeFehler = true;
                }

            } while (eingabeFehler);

            System.out.println("Danke! Jetzt bitte die zweite rationale Zahl in der gleich Form eingben '(+-)z/n' :");

            // Schleife für Eingabefehler des 2. Ratio-Strings
            do {
                stringRatio2 = scanner.next();

                if (stringRatio2.matches(pattern)) {
                    eingabeFehler = false;
                } else {
                    System.err.println("Bitte eine rationale Zahl in genau der Form '(+-)z/n' eingeben und die mathematischen Gesetze beachten...");
                    eingabeFehler = true;
                }

            } while (eingabeFehler);

            // Beide Ratio-Objekte werden aus den eingegebenen Strings erstellt.
            ratio1 = string2ratio(stringRatio1);
            ratio2 = string2ratio(stringRatio2);

            System.out.println("Bitte jetzt eienen Operator eingeben [+-*/]:");

            // Schleife für Eingabefehler der Rechenoperation
            do {
                operator = scanner.next();

                if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
                    eingabeFehler = false;
                } else {
                    System.err.println("Bitte einen gültigen Operator [+-*/] eingeben...");
                    eingabeFehler = true;
                }

            } while (eingabeFehler);

            // Abspeichern der Ratio-Objekte und des Operators im Speicher
            saveRatioZahl(ratio1);
            saveRatioZahl(ratio2);
            saveOperator(operator);

            // Rechenoperation wird in der aufgerufenen Methode - rechen() - aufgerufen
            rechnen(ratio1, ratio2, operator);

            // Abspeichern des Ergebnis-Objekts im Speicher
            saveRatioZahl(ergebnisRatio);

            // Ausgabe Ergebnis
            System.out.println("Ergebnis: " + ergebnisRatio.toString());

            System.out.println("Weiter rechnen? [j/n]");

            // Abfrage und gleichzeitige Zuweisung, ob weiter gerechnet werden soll
            weiter = scanner.next().equalsIgnoreCase("j");

        } while (weiter);

        // Ausgabe der Rechenergebnisse
        ergebnisAusgabe();
        
        // Speicher wieder leeren
        clearMemory();

    }// end of losgehts

    /**
     * ergebnisAusgabe-Methode:
     * List den Speicher und gibt alle vorgenommenen Rechnungen mit Ergebnis als
     * String aus.
     *
     */
    private void ergebnisAusgabe() {

        int anzahlRechnungen = RATIO_ZAHLEN.size() / 3;
        int temp = 0;

        System.out.println("\n---- Berechnungen ----");

        // Speicher (Rechnungen) auslesen und in der Schleife ausgeben
        for (int i = 1; i <= anzahlRechnungen; i++) {
            System.out.println("(" + restore(temp) + ") " + restoreOperator(temp / 3) + " (" + restore(temp + 1) + ") = " + restore(temp + 2));
            temp += 3;
        }

    }// end of ergebnisAusgabe

    /**
     * rechne-Methode:
     * Entscheidet welche Rechen-Methode aufgerufen werden soll und druft die
     * dazugehörige Methode auf.
     *
     * @param ratio1 (Ratio): erster Rochenoperant
     * @param ratio1 (Ratio): zweiter Rochenoperant
     * @param op    (String): Rechenoperator
     */
    private void rechnen(Ratio ratio1, Ratio ratio2, String op) {

        switch (op) {
            case "+":
                ergebnisRatio = ratio1.addiere(ratio2);
                break;
            case "-":
                ergebnisRatio = ratio1.subtrahiere(ratio2);
                break;
            case "*":
                ergebnisRatio = ratio1.multipliziere(ratio2);
                break;
            case "/":
                ergebnisRatio = ratio1.dividiere(ratio2);
                break;
            default:
                System.err.println("Unbekannter Rechenoperator eingegeben.");
                break;
        }
        
    }// end of rechnen

    /**
     * saveRatioZahl-Methode:
     * Sichert die rationale Zahl in Stringform in den Speicher (ArrayList) ab.
     *
     * @param ratio (Ratio): rationale Zahl
     */
    public void saveRatioZahl(Ratio ratio) {

        RATIO_ZAHLEN.add(String.valueOf(ratio));
        
    }// end of saveRatioZahl

    /**
     * saveOperator-Methode:
     * Sichert den Operator als String in den Speicher (ArrayList) ab.
     *
     * @param op (String): Rechenoparator
     */
    public void saveOperator(String op) {

        OPERATOREN.add(op);
        
    }// end of saveOperator

    /**
     * restore-Methode:
     * Gibt zum übergebenen Index den passenden String aus dem Speicher (ArrayList) zurück.
     *
     * @param index (int): die Position des String im Speicher (ArrayList)
     *
     * @return value (String): Rechenoperator
     */
    public String restoreOperator(int index) {

        String value = OPERATOREN.get(index);
        return value;
        
    }// end of restoreOperator

    /**
     * restore-Methode:
     * Gibt zum übergebenen Index den passenden String aus dem Speicher (ArrayList) zurück
     *
     * @param index (int): die Position des String im Speicher (ArrayList)
     *
     * @return value (String): Ratio-Objekt
     */
    public String restore(int index) {

        String value = RATIO_ZAHLEN.get(index);
        return value;
        
    }// end of restore

    /**
     * string2ratio-Methode:
     * Konvertiert einen String in eine rationale Zahl (Ratio-Objekt) und gibt 
     * dieses zurück. Der String wird in Zähler und Nenner aufgesplittet.
     *
     * @param strRatio (String): enthält die rationale Zahl in Stringform "2/3"
     *
     * @return ratio (Ratio): die Zahl als rationale Zahl
     */
    private Ratio string2ratio(String strRatio) {

        String[] splitStringRatio = strRatio.split("/");
        int z = 1, n = 1;

        try {
            z = Integer.parseInt(splitStringRatio[0]);
            n = Integer.parseInt(splitStringRatio[1]);

        } catch (NumberFormatException e) {
            System.err.println("Eingegebene Zahl war zu hoch für den Datentype Integer: " + e.getMessage() + " Es wird mit 1 weitergerechenet.");
        }

        Ratio ratio = new Ratio(z, n);

        return ratio;
        
    }// end of string2ratio

    /**
     * clearMemory-Methode:
     * Leert die ArrayList mit den Rechenoptionen und das mit den Ratio-Objekten.
     * Wird am Ende des Programms aufgerufen.
     */
    private void clearMemory() {

        RATIO_ZAHLEN.clear();
        OPERATOREN.clear();

        System.out.println("\nSpeicher bereinigt.\n");
        
    }// end of clearMemory

}// end of class
