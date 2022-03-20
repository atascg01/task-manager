FROM mysql
ENV MYSQL_ROOT_PASSWORD #ROOT_PASSWORD
ADD scripts/init.sql /docker-entrypoint-initdb.d
EXPOSE 3306