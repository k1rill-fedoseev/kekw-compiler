FROM openjdk:16-slim-buster as builder

WORKDIR /kekw

RUN apt-get update; \
    apt-get install -y gcc curl make m4; \
    curl -o bison.tar.gz https://ftp.gnu.org/gnu/bison/bison-3.7.1.tar.gz; \
    tar xvzf bison.tar.gz; \
    cd bison-3.7.1 && ./configure && make && make install

COPY src src

RUN /usr/local/bin/bison -o src/parser/Parser.java src/parser/parser.y; \
    javac -d out $(find src -name *.java)

ENTRYPOINT ["bash"]


FROM openjdk:16-slim-buster

WORKDIR /kekw

COPY --from=builder /kekw/out ./out

ENTRYPOINT ["java", "-cp", "out"]

CMD ["Main"]
