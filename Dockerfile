FROM openjdk:11
EXPOSE 8081
ADD target/risk-calculator.jar risk-calculator.jar
ENTRYPOINT ["java","-jar","/risk-calculator.jar"]