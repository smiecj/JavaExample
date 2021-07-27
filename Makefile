run:
	mvn clean package -DskipTests
	java -Dserver.port=8081 -jar target/rest-service-complete-0.0.1-SNAPSHOT.jar

test_command:
	curl http://localhost:8081/command/exec