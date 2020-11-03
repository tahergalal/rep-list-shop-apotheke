## Building
```./mvnw clean install```
1. This will remove the target folder 
2. It will also build the jar and run the tests

## Running
### Outside Docker
If you want to run outside docker you can simply run it as a normal java application with 
```bash
java -jar target/github-repository-list-0.0.1-SNAPSHOT.jar
```
### Within Docker
If you want to build a docker image then you can run with 
```
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
```
followed by 
```
docker build -t github-repository-list .
```

lastly you would run
```
docker run github-repository-list
```

A dockerized Image can be easily pushed to a repository and then further used in kubernetes by
simply creating a deployment and service which use the built docker image.