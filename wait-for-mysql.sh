#!/bin/sh

# Wait for the MySQL database to be up and running
until mysqladmin ping -h"$1" --silent; do
    echo 'Waiting for MySQL to be ready...'
    sleep 3
done

exec java -jar ./target/accenture-techhub-0.0.1-SNAPSHOT.war