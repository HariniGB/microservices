FROM java
EXPOSE 8080
ADD ./eazydineaapp-menu-services-0.0.1.jar eazydineaapp-menu-services-0.0.1.jar
RUN bash -c 'touch /eazydineaapp-menu-services-0.0.1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eazydineaapp-menu-services-0.0.1.jar"]
