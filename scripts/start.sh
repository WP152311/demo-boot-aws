#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)  # 현재 start.sh가 속해 있는 경로
source ${ABSDIR}/profile.sh # profile.sh 의 내용 import

REPOSITORY=/home/ec2-user/app/step3

PROJECT_NAME=demo-boot-aws

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar| tail -n 1)

echo "> JAR Name : $JAR_NAME"

echo "> JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

PROP_DIR=/home/ec2-user/app

nohup /home/ec2-user/jdk-14.0.1/bin/java -jar \
-Dspring.config.location=$PROP_DIR/application-oauth.properties,$PROP_DIR/application-real-db.properties \
-Dspring.profiles.active=$IDLE_PROFILE \
 $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
