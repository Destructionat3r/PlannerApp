package com.sid1818713.plannerapp.fragments.bankholidays.api

data class BankHolidayJsonItem(
    val counties: List<String>,
    val countryCode: String,
    val date: String,
    val fixed: Boolean,
    val global: Boolean,
    val launchYear: Int,
    val localName: String,
    val name: String,
    val type: String
)