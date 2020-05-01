#!/usr/bin/env bash
docker stop kit_db_postgres
docker rm kit_db_postgres
docker-compose up --build -d