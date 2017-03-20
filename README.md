# StackCalc
Stack Calculator as a Project for [TH Bingen Coding Camp][video]

Dieser Taschenrechner ist ein Demo Projekt für das Coding Camp der TH Bingen am 25./26. März 2017

## Funktionsweise

Der Taschenrechner arbeitet nach dem [Stack][stack] Prinzip. Das Wort Stack kommt aus dem englischen
und bedeutet Stapel. Wie bei einem Stapel kann man mit dem Stack Taschenrechner nur Zahlen oben auf
den Stapel legen und auch nur von oben wieder vom Stapel entfernen.

Das heißt:

- Erste Zahl eingeben
- Enter schiebt die erste Zahl oben auf den Stack
- Zweite Zahl eingeben
- Enter schiebt die zweite Zahl, über die erste Zahl, oben auf den Stack
- Jetzt kann man die Operation eingeben z.B. Addieren
    - Dabei werden die beiden Zahlen vom Stack geholt
    - Die Zahlen werden addiert
    - Das Ergebnis wird wieder auf den Stack gelegt und auf dem Display angezeigt

[video]:https://youtu.be/KaD0M8ueda8
[stack]:https://de.wikipedia.org/wiki/Stapelspeicher

## Wichtige Dateien

- Die Datei StackCalc.java enthält die Taschenrechner Logik
- Die Datei activity_stack_calc.xml enthält die Beschreibung wie die Oberfläche der App aussehen soll
- Diese beiden Dateien zusammen machen den großteil der Funktionalität der App aus