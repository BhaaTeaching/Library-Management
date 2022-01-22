#FROM adoptopenjdk/openjdk11:alpine-jre
#VOLUME /tmp
#EXPOSE 8080
#ADD build/libs/library-management-0.0.1-SNAPSHOT.jar library-management.jar
#ENTRYPOINT ["java", "-jar", "library-management.jar"]

FROM adoptopenjdk/openjdk11:alpine-jre
ADD build/libs/library-management-0.0.1-SNAPSHOT.jar library-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "library-management.jar"]



