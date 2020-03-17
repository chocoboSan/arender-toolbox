FROM debian:jessie-slim
ENV HOST_NAME="http://localhost:20000"
ENV NUMBER_REQUESTS=200
RUN apt update && apt install curl -y
ADD entrypoint.sh /entrypoint.sh
CMD ["/entrypoint.sh"]