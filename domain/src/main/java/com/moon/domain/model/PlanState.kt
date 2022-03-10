package com.moon.domain.model

enum class PlanState(var dbVale: String, var uiValue: String) {
    SUCCESS("SUCCESS", "완료"),
    FAIL("FAIL", "실패"),
    WAITING("WAITING", "대기"),
    CANCEL("CANCEL", "취소"),
    LATER("LATER", "내일"),
}