FROM java:8
MAINTAINER lxh
COPY ./target/demo*.jar /opt/app/axon-demo.jar
EXPOSE 8090
CMD ["java", "-jar", "/opt/app/axon-demo.jar"]
