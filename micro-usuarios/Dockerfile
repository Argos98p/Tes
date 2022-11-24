FROM openjdk:11 as mysqldoc
EXPOSE 8084
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
FROM openjdk:11
MAINTAINER baeldung.com
COPY target/user-micro-1.0.jar user-micro-1.0.jar
ENTRYPOINT ["java","-jar","/user-micro-1.0.jar"]