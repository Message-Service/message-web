#!/bin/bash

# 환경변수 설정
if [ -z "$APP_HOME" ]; then
        echo "설정: env..."
        source /root/script/env.sh
fi

# 디렉터리 확인
if [ ! -d "$APP_HOME" ]; then
        echo "오류: $APP_HOME 디렉터리는 존재하지 않습니다."
        exit 1
fi

# 모든 브랜치 가져오기
git -C "$APP_HOME" fetch --prune

# 브랜치 확인
BRANCH_NAME="$1"
if [ -n "$BRANCH_NAME" ]; then
        # 원격 브랜치 확인
        REMOTE_BRANCHS="$(git -C "$APP_HOME" branch --remote)"
        if ! grep -q "origin/$BRANCH_NAME" <<< "$REMOTE_BRANCHS"; then
                echo "오류: 원격에 브랜치가 존재하지 않습니다."
                printf "$REMOTE_BRANCHS\n"
                exit 1
        fi

        # 로컬 브랜치 확인 및 브랜치 변경
        LOCAL_BRANCHS="$(git -C "$APP_HOME" branch --list "$BRANCH_NAME")"
        if grep -q "$BRANCH_NAME" <<< "$LOCAL_BRANCHS"; then
                git -C "$APP_HOME" checkout "$BRANCH_NAME"
        else
                git -C "$APP_HOME" checkout -b "$BRANCH_NAME"
        fi
fi

# pull
git -C "$APP_HOME" pull origin "$(git -C "$APP_HOME" rev-parse --abbrev-ref HEAD)"

# 빌드
"$APP_HOME"/gradlew --project-dir "$APP_HOME" build