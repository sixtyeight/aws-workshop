### Platform as a Service (dynamoDB)

Im Rahmen der PaaS-Übung "dynamoDB" wollen wir uns näher mit der Cloud noSQL 
Datenbank dynamoDB beschäftigen. 

dynamoDB bietet die Möglichkeit, wie bei noSQL Datenbanken üblich, 
unstrukturierte Daten in Tabellen abzuspeichern und auf diese - je nach Berechtigungen - 
lesend und schreibend zuzugreifen.

Die Abfrage von Daten erfolgt dabei entweder über eine Query oder ein Scan. 
Während bei einer Query nur die Schlüsselfelder einer Tabelle betrachtet 
werden, erfolgt bei einem Scan eine Suche über alle Spalten der angesprochenen Tabelle(n).   

Der Zugriff auf dynamoDB kann via Web Console, AWS CLI oder programmativ 
via Java SDK erfolgen. 

#### Zugriff via AWS Web Console

 Für den Zugriff auf AWS dnymamoDB via Web Console muss sich zunächst als 
 AWS Nutzer angemeldet werden:   
  
 1. AWS Console aufrufen        
 
 2. Mit AWS Nutzerdaten einlogen          

Durch die anschließende Auswahl des Services dynamoDB gelangt man direkt 
zu "seinen" Tabellen (so bereits welche vorhanden sind). Die Web-Oberfläche 
ist selbsterklärend. 

Es lassen sich an dieser Stelle folgende Funktionen ausführen: 

   1. Tabellen anlegen/löschen 
   2. Items in die Tabelle einfügen 
   3. Items der Tabelle anschauen, bearbeiten und löschen
   3. Queries gegen die Tabelle absetzen
   4. Scans gegen die Tabelle absetzen
   
Am besten einfach ein wenig "hermumspielen".
 
#### Zugriff via AWS Java SDK  

Für das Ausprobieren des AWS Java SDKs für dynamoDB haben wir einen "Playground"
vorbereitet. Der Playground sowie alle zur Umsetzung der dort enthaltenen 
Übungen notwendigen Erläuterungen finden sich unter:  

    de.openknowledge.sample.cloud.aws.paas.dynamodb.AwsDynamoDbPlayground

ACHTUNG: Der Zugriff auf das AWS Account wird über eine kleine 
Helper-Klasse AwsAccount im Modul 00_base geregelt. Bitte dort zunächst 
das TODO bearbeiten und die folgende Zeile anpassen: 

    public final static String OWNER = "YOUR_OWNER_NAME_HERE";
  
ANMERKUNG: Um AWS DynamoDb aus Java heraus ansprechen zu können, muss das 
AWS Java DynamoDB SDK als Abhängigkeit in die Maven POM eingebunden werden 
(siehe auch pom.xml im Modul 02_02_dynamoDB): 

    <!-- AWS dynamoDb dependencies -->
	<dependency>
		<groupId>software.amazon.awssdk</groupId>
		<artifactId>dynamoDb</artifactId>
		<version>2.0.0-preview-4</version>
	</dependency>  
	  
Viel Spass beim "herumspielen" 
