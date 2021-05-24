package com.sid1818713.plannerapp.fragments.bankholidays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.sid1818713.plannerapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class BankHolidays : Fragment() {
    private lateinit var bankHolidayList: ListView
    private val BASE_URL="https://date.nager.at/"

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bank_holidays, container, false)
        bankHolidayList = view.findViewById(R.id.bankHoliday_lv)

        val api = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getBankHolidays().awaitResponse()
            if (response.isSuccessful) {
                val data = response.body()!! //not null
                val holidayList = arrayOfNulls<String>(data.size)

                for (i in 0 until data.size) {
                    val holiday = data[i]
                    holidayList[i] = "${holiday.name} - ${holiday.date}"
                }

                activity?.runOnUiThread {
                    val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, holidayList)
                    bankHolidayList.adapter = adapter
                }
            }
        }

        return view
    }
}
