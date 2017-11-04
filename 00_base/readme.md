### AWS Cloud Workshop - base  

Im Rahmen des AWS Cloud Workshops führen wir über mehrer Übungen 
in den Umgang mit verschiedensten AWS Cloud-Komponenten und 
-Services ein. 

Um dabei nicht jedes Mal seine AWS Account-Daten neu angeben zu müssen,
haben wir diese Aufgabe, sowie den Zugriff auf die verschiedenen 
Plattform-Clients (wie z.B. S3Client und DynamoDbClient) in einem 
eigenen Helper-Modul gekapselt. 

Damit im Verlaufe der Übungen die einzelnen Teilnehmer "ihre" Arbeit 
von der der anderen Teilnehmer unterscheiden können, sollte sich jeder 
Teilnehmer einen eigenen Präfix ausdenken und diesen in der Klasse AwsAccount 
hinterlegen. Die anderen Übungen greifen auf diesen Präfix zu.

Zu diesem Zweck bitte das TODO in der Klasse AwsAccount 
bearbeiten und die folgende Zeile anpassen: 
                                   
    public final static String OWNER = "YOUR_OWNER_NAME_HERE";
                                      
ACHTUNG: Das aus dem Moudul 00_base resultierende Maven-Artifact 
aws-common-exercises wird von den meisten anderen Übungen verwendet. 
Es muss also zunächst durch Aufruf des folgenden Befehls im Hauptverzeichnis 
des Moduls 00_base im lokalen Maven-Repository installiert werden: 

    mvn clean install 
    