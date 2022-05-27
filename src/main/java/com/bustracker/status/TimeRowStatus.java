package com.bustracker.status;

public enum TimeRowStatus {
    // 대기
    STAND_BY,

    // 운행 중
    IN_PROGRESS,

    // 운행 종료
    COMPLETE,

    // 운행 안함
    NOT_PROGRESS,

    // 지연
    DELAY
}
