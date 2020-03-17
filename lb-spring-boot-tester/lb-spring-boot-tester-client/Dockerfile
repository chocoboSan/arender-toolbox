FROM adoptopenjdk:8-hotspot
COPY ./target/*.jar /arender/app.jar

ARG USER=arender
ARG GROUP=arondor
RUN addgroup --system ${GROUP} && adduser --system --ingroup ${GROUP} ${USER} &&\
    chown -R ${USER}:${GROUP} /arender && chmod 755 -R /arender/ && usermod -d /arender/ ${USER}
USER ${USER}

WORKDIR /arender/
CMD ["java", "-jar", "app.jar"]