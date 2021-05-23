package com.sid1818713.plannerapp.fragments.bankholidays

import com.sid1818713.plannerapp.fragments.bankholidays.api.BankHolidayJson
import retrofit2.Call
import retrofit2.http.GET

interface ApiRequests {
    @GET("/api/v2/NextPublicHolidays/GB")
    fun getBankHolidays(): Call<BankHolidayJson>
}