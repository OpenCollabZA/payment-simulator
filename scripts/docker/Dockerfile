FROM opencollab-docker.opencollab.co.za/opencollab/jdk8:b49

ADD payment-simulator.jar /payment-simulator.jar

EXPOSE 8080

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -jar /payment-simulator.jar" ]
