### Function as a Service

Im Rahmen dieser Übung wollen wir eine einfache AWS Lambda-Funktion erstellen 
und im Anschluß live schalten.

Zu diesem Zweck sind vier Schritte notwendig: 

   1. Lambda-Funktion entwickeln 
   2. Lambda-Funktion + Abhängigkeiten als JAR packen
   3. JAR der Lambda-Funktion in AWS Cloud deployen
   4. Lambda-Funktion testen/ausführen
      
#### Lambda-Funktion entwickeln

Die zu entwickelnde Lambda-Funktion sowie alle zur Finalisierung 
notwendigen Erläuterungen finden sich unter:  

`de.openknowledge.sample.cloud.aws.lambda.function.HelloWorkshop`

#### Lambda-Funktion builden & packen

Zum Packen der Lambda-Funkltion inklusive aller Abhängigkeiten einfach in das 
entsprechende Projektverzeichnis wechseln und maven aufrufen 

    cd ./04_faas
    mvn clean package 

Nach erfolgreichem Build sollten sich im "/target"- Verzeichnis die folgenden 
zwei Datein befinden: 

    myFirstLambdaFunction.jar
    original-myFirstLambdaFunction.jar
   
#### Lambda-Funktion deployen

Die Lambda-Funktion kann entweder via AWS Command Line Interface (AWS CLI) 
oder direkt über die AWS Web Console deployed werden. Wir wählen 
den Weg über die AWS Web Console. Folgende Schritte sind notwendig: 

  - Console aufrufen
  - mit AWS Nutzerdaten einloggen 
  - neue Lambda-Funktion anlegen
  - Lambda-Funktion konfigurieren  
   
1. Console aufrufen      

2. Mit AWS Nutzerdaten einlogen 

3. Neue Lambda-Funktion anlegen 

        [OWNER].myFirstLambda
        java8 
        
      Anmerkung: [OWNER] durch eigenes Kürzel ersetzen. 
        
4. Lambda-Funktion konfiguieren

        de.openknowledge.sample.cloud.aws.lambda.function.HelloWorkshop::handleRequest


#### Lambda-Funktion testen

Die eben in der AWS Cloud deployte Lambda-Funktion kann direkt von der AWS Web Console
aus getestet werden. Wir wollen sowohl einen Test mit "korrekten" Daten als auch einen Test 
ohne Daten durchführen.

Für den ersten Test nutzen wir als Testdaten die folgende Eingabe:  

    { 
        "name" : "Cloud Workshop 2017"
    }    
 
Die Lambda-Funktion sollte den in der Funktion erzeugten String als JSON zurückgeben. Also 
z.B.  

    { 
        "greetings" : "Hallo auf dem Cloud Workshop 2017"
    }    
 
Für den zweiten Test lassen wir den Test-Record bewusst leer. Der Rückgabewert sollte entsprechend der 
in der Lambda-Funktion programmierten Fallback-Logik aussehen. 
   