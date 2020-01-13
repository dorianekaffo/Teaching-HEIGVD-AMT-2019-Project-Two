FROM maven:3.3.9-jdk-8-alpine

ADD tmp specs
ADD run-tests.sh run-tests.sh

ENTRYPOINT ["./run-tests.sh"]
