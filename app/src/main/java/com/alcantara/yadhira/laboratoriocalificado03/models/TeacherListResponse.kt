package com.alcantara.yadhira.laboratoriocalificado03

data class TeacherListResponse(
    val count: Int,
    val next: String?,
    val teachers: List<TeacherResponse>
)
