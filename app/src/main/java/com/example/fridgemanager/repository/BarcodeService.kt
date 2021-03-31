package com.example.fridgemanager.repository

import retrofit2.http.GET
import retrofit2.http.Path

interface BarcodeService {

    // 상수가 저장되는 객체
    companion object {
        const val BASE_URL =
                "http://openapi.foodsafetykorea.go.kr/api/66fedc6c65a848e29fb9/C005/json/1/5/"
    }

    @GET("BAR_CD")
    suspend fun fetchInfo (
            @Path("barcode") barcode: String
    )
}