### Platform as a Service (S3)

Im Rahmen der PaaS-Übung "S3" wollen wir uns näher mit dem Cloud Storage 
Systen AWS S3 beschäftigen. 

S3 bietet die Möglichkeit via Buckets Ablagestrukturen in der Cloud zu erstellen 
und dort beliebige Objekte inkl. Metadaten abzulegen bzw. auf diese Objekte und 
Metadaten - je nach Berechtigungen - lesend und schreibend 
zuzugreifen.  

Der Zugriff auf S3 kann via Web Console, AWS CLI oder programmativ 
via Java SDK erfolgen. 

#### Zugriff via AWS Web Console

 Für den Zugriff auf AWS S3 via Web Console muss sich zunächst als 
 AWS Nutzer angemeldet werden:   
  
 1. AWS Console aufrufen  
 
 2. Mit AWS Nutzerdaten einlogen

Durch die anschließende Auswahl des Services S3 gelangt man direkt 
zu "seinen" S3 Buckets (so bereits welche vorhanden sind). Die Web-Oberfläche 
ist selbsterklärend. 

Es lassen sich an dieser Stelle folgende Funktionen ausführen: 

   1. Bucket anlegen/löschen 
   2. Objekte in Bucket legen (inkl. Metadaten und Berechtigungen)
   3. Objekte aus Bucket kopieren
   4. Objekte aus Bucket löschen
   
Am besten einfach ein wenig "hermumspielen".
 
#### Zugriff via AWS Java SDK  

Für das Ausprobieren des AWS Java SDKs für S3 haben wir einen "Playground"
vorbereitet. Der Playground sowie alle zur Umsetzung der dort enthaltenen 
Übungen notwendigen Erläuterungen finden sich unter:  

    de.openknowledge.sample.cloud.aws.paas.s3.AwsS3Playground

ACHTUNG: Der Zugriff auf das AWS Account wird über eine kleine 
Helper-Klasse AwsAccount im Modul 00_base geregelt. Bitte dort zunächst 
das TODO bearbeiten und die folgende Zeile anpassen: 

    public final static String OWNER = "YOUR_OWNER_NAME_HERE";
  
ANMERKUNG: Um AWS S3 aus Java heraus ansprechen zu können, muss das 
AWS Java S3 SDK als Abhängigkeit in die Maven POM eingebunden werden
(siehe auch pom.xml im Modul 02_01_s3):  

    <!-- AWS S3 dependencies -->
	<dependency>
		<groupId>software.amazon.awssdk</groupId>
		<artifactId>s3</artifactId>
		<version>2.0.0-preview-4</version>
	</dependency>  
  
Viel Spass beim "herumspielen" 
