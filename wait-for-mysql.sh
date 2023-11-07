#!/bin/sh

# Wait for the MySQL database to be up and running
until mysqladmin ping -h"$1" --silent; do
    echo 'Waiting for MySQL to be ready...'
    sleep 3
done

# Run the Java application
#exec "$2" "/app/app.war"
exec java -jar /app/app.war