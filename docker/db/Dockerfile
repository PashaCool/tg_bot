FROM postgres:latest
RUN localedef -i ru_RU -c -f UTF-8 -A /usr/share/locale/locale.alias ru_RU.UTF-8
ENV LANG=ru_RU.utf8
EXPOSE 5432
COPY init.sql /docker-entrypoint-initdb.d/