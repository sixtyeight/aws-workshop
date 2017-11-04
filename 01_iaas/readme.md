### Infrastructure as a Service

Im Rahmen dieser Übung wollen wir einen einfachen RESTful-Service zur Verwaltung von Images und Image-Metadaten statt auf einem eigenen On-Premises Server in der Cloud deployen. 

Damit wir uns zur Laufzeit nicht um Aspekte wie Skalierung, Load-Balancing, Routing usw. kümmern müssen, nutzen wir den AWS Service Elastic Beanstalk.  

Elastic Beanstalk bietet verschiedene Möglichkeiten des Deployments: 

   1. Deployment via Docker-Container
   2. Deployment eines WARs in GlassFish 5.0.0
   3. Deployment eines JARs in einer Java 8 Laufzeitumgebung 

Für die Übung erstellen wir ein sogenanntes uber-jar, also ein JAR, welches sowohl den RESTFul Image-Service selbst, als auch die notwendigen Runtime-Komponenten enthält und deployen diesen in einer Java 8 Umgebung in der Cloud (Variante 3).   

Zum Deployen eines JARs in einer durch Elastic Beanstalk gemanagten Java 8 Laufzeitumgebung sind mehrere Schritte notwendig:

   1. uber-jar erzeugen
   2. uber-jar in der AWS Cloud via Elastic Beanstalk deployen
      
#### ueber-jar erzeugen

Zum Erzeugen des uber-jar nutzen wir das Maven-Plugin "wildfly-swarm-plugin". Das Plugin baut in dem Verzeichnis ./target neben dem WAR (aws-iaas-exercises.war) zusätzlich noch das gewünschte uber-jar (aws-iaas-exercises-swarm.jar), welches direkt von der Commandline gestartet werden kann (siehe auch pom.xml im Verzeichnis 01_iaas).  

#### uber-jar in der AWS Cloud via Elastic Beanstalk deployen

Das uber-jar kann entweder via AWS Command Line Interface (AWS CLI) oder direkt über die AWS Web Console deployed werden. Wir wählen den Weg über die AWS Web Console. Folgende Schritte sind dafür notwendig. 

  - Console aufrufen
  - mit AWS Nutzerdaten einloggen 
  - Elastic Beanstalk aufrufen
  - uber-jar deployen  
   
1. AWS Console aufrufen

2. Mit AWS Nutzerdaten einlogen

3. Elastic Beanstalk aufrufen

    Unter den AWS Services Elastic Beanstalk auswählen. Dies führt direkt zur Beanstalk Web-Oberfläche, welche (fast) selbsterklärend ist.   

4. uber-jar deployen

   Zum Deployen des uber-jars und damit unseres RESTful Services muss zunächst eine neue Elastic Beanstalk Anwendung erzeugt werden. Dazu gilt es den Menu-Punkt 
   
	   	Create New Application 

	aufzurufen und im anschließend erscheinenden Fenster einen Namen und eine Beschreibung einzugeben. Für den Namen bitte einen eindeutigen Präfix verwenden (z.B. "maxMustermann"), so dass die Anwendung später von den Anwendungen der anderen Workshop-Teilnehmer leicht zu unterscheiden ist: 

		maxMustermann.image.service
		
		
	Die erzeugte Anwendung dient zunächst einmal nur als eine Art Rahmen für verschiedene Environments (Deployments). 
	
	Im nächsten Schritt gilt es nun ein entsprechendes Web-Server Environment anzulegen und dort das uber-jar zu deployen. 
	
	Klingt kompliziert? Ist es aber nicht. Die Elastic Beanstalk Oberfläche führt durch die notwendigen Schritte. Und natürlich gehen wir diese Übung gemeinsam am. 
	
Welcome to the Cloud ;-)
	
	
 