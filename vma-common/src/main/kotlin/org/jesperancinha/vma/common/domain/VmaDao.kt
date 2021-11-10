package org.jesperancinha.vma.common.domain

import org.jesperancinha.vma.common.dto.CategoryType


data class VmaSongDto(
    val name: String,
    val artists: List<String>,
    val types: List<CategoryType>
)
