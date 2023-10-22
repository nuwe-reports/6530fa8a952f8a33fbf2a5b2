#!/bin/sh

# Wait for the MySQL database to be up and running
until mysqladmin ping -h"$1" --silent; do
    echo 'Waiting for MySQL to be ready...'
    sleep 2
done

# Run the Java application
exec "$2" "${@:3}"
