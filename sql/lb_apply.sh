#!/bin/sh
LB_HOME=/ssd/java/liquibase
$LB_HOME/liquibase --driver=org.postgresql.Driver \
--classpath=$LB_HOME/lib:/home/cpv/.m2/repository/org/postgresql/postgresql/42.1.4/postgresql-42.1.4.jar \
--changeLogFile=databaseChangeLog.sql \
--url="jdbc:postgresql://localhost:5432/masterjava" \
--username=user \
--password=password \
migrate