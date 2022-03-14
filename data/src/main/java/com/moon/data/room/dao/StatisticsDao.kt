package com.moon.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.moon.data.room.entity.ResultStateCountData
import com.moon.data.room.entity.ResultTagCountData
import java.util.*

@Dao
interface StatisticsDao {
    // 1. 상태별 건수 조회
    @Query(
        """
        select count(*) as "total_count",
        count(case when plan_state="SUCCESS" then 0 end) as "success_count",
        count(case when plan_state="FAIL" then 0 end) as "fail_count",
        count(case when plan_state="CANCEL" then 0 end) as "cancel_count"
        from plan_table
        where 
        plan_date >= :startDate
        and
        plan_date <= :endDate
    """
    )
    suspend fun getResultStateCount(startDate: Date, endDate: Date): ResultStateCountData

    // 2. 태그별 건수 조회
    @Query(
        """
        select 
        plan_tag_id,
        tag_name,
        tag_color,
        count(*) as "count",
        count(case when plan_state="SUCCESS" then 0 end) as "success_count"
        from plan_table left join tag_table on plan_table.plan_tag_id = tag_table.tag_id 
        where 
        plan_date >= :startDate
        and
        plan_date <= :endDate
        group by plan_tag_id;
    """
    )
    suspend fun getResultTagCount(startDate: Date, endDate: Date): List<ResultTagCountData>
}