FROM alpine:3.9.5
ENV HOST_NAME="http://localhost:20000"
ENV NUMBER_REQUESTS=200
RUN apk add --no-cache curl
ADD entrypoint.sh /entrypoint.sh
CMD ["/entrypoint.sh"]