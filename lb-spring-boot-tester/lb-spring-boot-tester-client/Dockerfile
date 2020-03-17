FROM adoptopenjdk/openjdk8:alpine-slim

ARG USER=arender
ARG GROUP=arondor
RUN mkdir /arender && addgroup --system ${GROUP} && adduser --system --ingroup ${GROUP} ${USER} &&\
    chown -R ${USER}:${GROUP} /arender && chmod 755 -R /arender/
USER ${USER}

COPY ./target/*.jar /arender/app.jar
WORKDIR /arender/
CMD ["java", "-Dsun.net.inetaddr.ttl=0", "-jar", "app.jar"]