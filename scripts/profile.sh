#!/usr/bin/env bash

# 쉬고 있는 port 찾기
function find_idle_port()
{
    IDLE_PORT=$(curl -s http://localhost/port)

    if [ ${IDLE_PORT} == 8081 ]
    then
      echo "8082"
    else
      echo "8081"  # defalult port는 8081
    fi
}