from openjdk:16-slim

RUN addgroup --system shopapotheke && adduser --system  shopapotheke
USER shopapotheke:shopapotheke

ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.shopapotheke.githubrepositorylist.GithubRepositoryListApplication"]