mvn clean install -DskipTests

docker build -t kelvn/standard-springboot-api:latest -f Dockerfile .

docker push kelvn/standard-springboot-api:latest