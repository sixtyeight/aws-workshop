### Backend as a Service

Im Rahmen der BaaS Übung wollen wir uns mit verschiedenen Aspekten der Anbindung eines Cloud-basierten Backends beschäftigen. Als Backend nutzen wir den in ```01_iaas``` deployten RESTful Image Servcie. 

ANMERKUNG: Wir stellen uns vor, der Servcie sei so konfiguriert, dass er von aussen nicht direkt erreichbar ist. Dies entspräche eine realistischeren Konfiguration, als wir sie im Modul ```01_iaas``` vorgenommen haben. 

Zur Anbidnung gehen wir in drei Schritten vor: 

   - API Gateway erstellen und für den Zugriff auf den Cloud-basierten Service konfigurieren 
   - API Gateway um Token-basierte Security erweitern 
   - API Gateway um Response-Mappings erweitern

ANMERKUNG: Um die erfolgreiche Umsetzung der Übrungen und somit den Zugriff auf das Gateway besser testen zu können, haben wir eine kleine Web-Anwenung gebaut und innerhalb des Moduls ```03_baas``` zur Verfügung gestellt. Zum Starten der Web-Anwendung einfach folgendes aus dem Verzeichnis ```03_baas``` heraus aufrufen: 

	./build/index.html
	
#### API Gateway erstellen und kunfigurieren

Bei einem AWS API Gateway handelt es sich, wie schon zurvor bei AWS S3 und AWS DynamoDb, um eine von AWS gemanagte Komponente. 

Wie die anderen Komponenten auch, kann das API Gateway via AWS CLI (Command Line Interface) oder dirket über die AWS Web Console angelegt und konfiguriert werden. Wir wählen den Weg über die AWS Web Console. Folgende Schritte sind notwendig: 

  - Console aufrufen
  - mit AWS Nutzerdaten einloggen 
  - neues AWS API Gateway anlegen
  - AWS API Gateway konfigurieren  
  - AWS API Gateway deployen
   
  
##### Console aufrufen 
      
##### Mit AWS Nutzerdaten einlogen        

##### Neues AWS API Gateway anlegen

Um ein neues API Gateway anzulegen, rufen wir zunächst ```Create API``` in dem AWS Service API Gateway auf. Dort werden wir aufgefordert folgende Metadaten für die neue API anzugeben: 
	- Name 
	- Beschreibung 

Um das eigene API Gateway später von den API Gateways der anderen Workshop-Teilnehmer besser unterscheiden zu können, sollte ein eindeutiger Präfix gewählt werden (z.B. maxm für Max Mustermann). 
	
	maxm.images.api			

##### AWS API Gateway konfigurieren 

Ist das API Gateway angelegt, lassen sich verschiedene Aktionen darauf ausführen. Als erste Aktion wählen wir ```Create Resource``` und geben dort die anzusprechenden REST Ressourcen unseres bereits in der Cloud deployten IaaS Image Services an (erst ```api``` und dann ```images```): 
	
	/api/images
		
Im nächsten Schritt enablen wir für die neu angelegte Ressource ```ìmages``` CORS (Cross Origin Resouirce Sharing) via gleichnamiger Aktion.  
	
Im letzten Schritt der Konfiguration gilt es nun noch, die eingehenden Aufrufe auf unsere im Modul IaaS deployte RESTFul Schnittstelle zu delegieren. Um dies zu erreichen, legen wir zunächst - wieder via Aktion - eine HTTP GET Methode für die Resource images an und "schleusen" diese Eins zu Eins auf unsere bereits vorhandene IaaS Eleastic Beanstalk Instanz durch. Folgender Aufruf kann nun von unserem Gateway verarbeitet werden: 

    GET /api/images 
	    
##### AWS API Gateway deployen 

Unser API Gateway ist nun zwar konfiguriert, kann aber nach wie vor nicht von aussen erreicht werden. Dazu fehlt noch ein wichtiger Schritt: das Deployment.
	
Mit Hilfe der Aktion ```Deploy API``` können wir die eben konfigurierte API auf einer bestehenden oder neuen Stage (z.B. workshop) deployen. Als Resultat des gelungenen Deployments erhalten wir ein URL des API Gateways für die ausgewählte Stage. 
	
    GET https://[MyGatewayStageURL]/api/images 			
Wenn alles fehlerfrei verlaufen ist, sollte die oben aufgezeigte URL eine Liste von Bildern aus unserer IaaS Cloud Anwendung - über den Umweg des Gateways - liefern.  
	        
#### API Gateway um Token-basierte Security erweitern

Im nächsten Schritt wollen wir unseren Backend Service via Gateway gegen unbefugte Aufrufer schützen. Das Gateway soll dabei sicherstellen, dass nur diejenigen Aufrufe bis zum Image Service gelangen, die im Vorfeld authentifiziert und authorisiert wurden. 

Um das gewünschte Ziel zu erreichen, sind wieder mehrere Schritte notwendig: 

  - Authorizer erstellen
  - HTTP Methode via Authorizer absichern 

##### Authorizer erstellen  

Im Submenu unseres API Gateways die Funktion ```Authorizer``` wählen und dort via ```Create new Authorizer```einen neuen Authorizer mit folgenden Werten erstellen. 

    Name: beliebig
    Typ: Cognito
    Cognito User Pool: vorhandenen Pool auswählen
    Token-Secure: Authorization

##### HTTP Methode via Authorizer absichern 

Nachdem wir den Authorizer erstellt und mit einem bestehenden AWS Cognito UserPool verbunden haben, gilt es im nächsten Schritt, diesen zur Absicherung unserer HTTP Methoden zu verwenden. Hierzu wählen wir unsere oben angelegte und konfigurierte Image Ressource aus 

	GET /images 
	
und legen im ```Method Request``` für die Authorization den eben angelegten Cognito User Pool Authorizer fest. Z.B: 

	my_authorizer
	
Ob die Konfiguration erfolgreich war, lässt sich einfach über die im Projekt ```03_baas```enthaltende Web Anwendung testen. Ein Aufruf ohne vorheriges Login sollte mit einer entsprechenden Fehlermeldung scheitern. 

Ein Aufruf mit korrekt eingeloggtem User dagegen sollte die gewünschten Image Daten liefern: 

	user name: max
	password: mustermann

#### API Gateway um Response-Mappings erweitern

Im letzten Übungsteil wollen wir das Gateway dazu nutzen, die Payload eingehender Requsts bzw. ausgehender Responses bewusst zu manipulieren. 

Dies macht zum Beispiel immer dann Sinn, wenn eine neue Version der API mit einer veränderten Payload bzw. Payload-Struktur im Backend deployt wurde, man aber weiterhin parallel auch die die alte Version der API und somit der Payload bzw. Payload-Struktur unterstützen möchte. Oder aber, wenn man einen 3rd Party Service via Gateway kapselt und nur ausgesuchte Teile des Responses an den Client weiterleiten möchte. Durch dieses Vorgehen erreicht man eine zusätzliche Entkopplung von Client und Service. 

Um das gewünschte Mapping vorzunehmen, wählen wir die zugeörige Ressource & HTTP Methode ```api/images  GET``` aus und geben dort unter ```Integration Response``` für den HTTP Status Code ```OK 200```die Zuordnung vom originärem Response des RESTful Image Services zur gewünschten Ziel-Payload an: 

- Zeile für Status Code 200 aufklappen 
- Body Mapping Template aufklappen
- Add Mapping Template anklicken
- Content Type angeben (application/vnd.acme.v1+json)
- Template eingeben und speichern 

 
Auch für den Bereich BaaS gilt natürlich wieder, dasss wir niemanden im Strich lassen und die Übungen gemeinsam durchspielen werden. 		 
