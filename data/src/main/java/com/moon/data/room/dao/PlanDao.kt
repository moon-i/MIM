package com.moon.data.room.dao

import androidx.room.*
import com.moon.data.room.entity.PlanEntity
import com.moon.data.room.entity.PlanWithTagData
import java.util.Date

@Dao
interface PlanDao {
    // 1. 계획 insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(plan: PlanEntity)

    // 2. 계획 delete - TODO 상태가 waiting, later인것만 호출하도록 보장하기
    @Delete
    suspend fun delete(plan: PlanEntity)

    // 3. 계획 상태 변경
    @Query("""
        update plan_table set plan_state = :state
        where plan_id = :planId
    """)
    suspend fun setPlanState(planId:Long, state: String)

    // 4. 계획 상태 변경 - 날짜가 지난 계획중 waiting이거나 later인것을 fail로 변경 (앱 기동시 비동기로 실행)
    @Query("""
        update plan_table set plan_state = "FAIL"
        where (plan_state = "WAITING" OR plan_state = "LATER")
        AND plan_date < :todayDate
    """)
    suspend fun setPlanStateBeforeToday(todayDate: Date)

    // 5. 계획 날짜 변경 - 미루기
    @Query("""
        update plan_table set plan_date = :newDate, plan_state = "LATER"
        where plan_id = :planId and (plan_state != "SUCCESS")
    """)
    suspend fun setPlanDelayOneDay(planId: Long, newDate: Date)

    // 6. 해당 날짜의 계획 조회하기
    @Query(
        """
        select plan_id, plan_name, plan_date, plan_state, plan_tag_id, tag_name, tag_color
        from plan_table left join tag_table on plan_table.plan_tag_id = tag_table.tag_id
        where plan_date = :date
    """
    )
    fun getPlanListThatDate(date: Date): List<PlanWithTagData>
}