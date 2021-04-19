#!/bin/sh

mvn install:install-file -Dfile=sqljdbc4.jar -Dpackaging=jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0.2206.100

mvn install:install-file -Dfile=mssql-jdbc-6.2.1.jre8.jar  -Dpackaging=jar -DgroupId=com.microsoft.sqlserver -DartifactId=mssql-jdbc -Dversion=6.2.1
