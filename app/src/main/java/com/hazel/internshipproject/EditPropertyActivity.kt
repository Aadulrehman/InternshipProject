package com.hazel.internshipproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter.ViewBinder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hazel.internshipproject.databinding.ActivityEditPropertyBinding
import com.hazel.internshipproject.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditPropertyActivity : AppCompatActivity() {
    private lateinit var viewBinder:ActivityEditPropertyBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList:MutableList<PropertyDetailsData>
    lateinit var db:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinder= ActivityEditPropertyBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)

        recyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList= mutableListOf<PropertyDetailsData>()
        getData()

        val adapter = PropertyEditAdapter(dataList)
        recyclerView.adapter = adapter

    }
    private fun getData()
    {
        db=AppDatabase.getInstance(this)
        GlobalScope.launch {
            val myPropertyList= db.propertyDao().getAll()
            val myAddressList=db.propertyAddressDao().getAll()
            CoroutineScope(Dispatchers.Main).launch {
                for(i in myPropertyList.indices){

                    val propertyDetailsData=PropertyDetailsData(
                        myAddressList[i].city,
                        myAddressList[i].address,
                        myPropertyList[i].room,
                        myPropertyList[i].bath,
                        myPropertyList[i].kitchen,
                        myPropertyList[i].floor,
                        myPropertyList[i].area,
                        myPropertyList[i].interior,
                        myPropertyList[i].purpose)
                    dataList.add(propertyDetailsData)
                }
            }
        }
        recyclerView.adapter=PropertyEditAdapter(dataList)
    }
}