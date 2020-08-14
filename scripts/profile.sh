#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile()
{
  # 현재 엔진엑스가 바라보고 있는 스프링 부트가 정상적으로 실행중인지 확인.
  # 응답값을 HttpStatus로 받음
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ 400 -le ${RESPONSE_CODE} ] # 400 이상이면; 4xx/5xx 에러 모두 포함
  then
    CURRENT_PROFILE=real2 # 현재 엔진엑스가 바라보는 스프링 부트가 에러이면 항상 real2 프로필 사용
  else
    CURRENT_PROFILE=$(curl -s http:localhost/profile)
  fi

  if [ "$CURRENT_PROFILE" == "real1" ]
  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if [ "$IDLE_PROFILE" == "real1"]
    then
      echo "8081"
    else
      echo "8082"
    fi
}
