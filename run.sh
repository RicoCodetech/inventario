#!/usr/bin/env bash

set -e

export DB_URL="${DB_URL:-jdbc:mysql://localhost:3306/inventario}"
export DB_USER="${DB_USER:-root}"

mvn -q package
java -jar target/inventario-tienda-1.0.0.jar