run:
	ps -ef | grep "rest-service-complete" | grep -v grep | awk '{print $$2}' | xargs --no-run-if-empty kill -9
	mvn clean package -DskipTests
	nohup java -Dserver.port=8081 -jar target/rest-service-complete-0.0.1-SNAPSHOT.jar > test.log 2>&1 &

greet:
	curl http://localhost:8081/greeting

exec:
	curl http://localhost:8081/command/exec

read:
	curl http://localhost:8081/hive/read?filePath=/tmp/test.log

encode:
	curl http://localhost:8081/command/encode