package com.example.roadmapaperolesson3exercise.core.common


sealed interface Resource<out T> { // out chỉ trả ra, không sửa -> an toàn
    data object Loading: Resource<Nothing> // Nothing là không có gì
    data class Success<T>(val data: T): Resource<T> // T là kiểu dữ liệu
    data class Error(val throwable: Throwable): Resource<Nothing>
}