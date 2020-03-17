FROM adoptopenjdk:8-hotspot

ARG USER=arender
ARG GROUP=arondor
RUN mkdir /arender && addgroup --system ${GROUP} && adduser --system --ingroup ${GROUP} ${USER} &&\
    chown -R ${USER}:${GROUP} /arender && chmod 755 -R /arender/
USER ${USER}

EXPOSE 20000
COPY ./target/*.jar /arender/app.jar
WORKDIR /arender/
CMD ["java", "-jar", "app.jar"]