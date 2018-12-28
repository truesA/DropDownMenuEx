package com.lhm.dropdownmenuex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import dropdownmenu.adapter.ListDropDownAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val types = arrayOf("未完成", "已完成", "我收到的", "我发出的").toList()
    private val headers = arrayOf("未完成")
    private lateinit var listAdapter: ListDropDownAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }


    private fun initView() {
        val listView = ListView(this)
        listView.dividerHeight = 0
        listAdapter = ListDropDownAdapter(this, types)
        listView.adapter = listAdapter

        val popupViews = ArrayList<View>()
        popupViews.add(listView)

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            listAdapter.setCheckItem(position)
            task_drop_down_menu.setTabText(if (position == 0) headers[0] else types[position])
            task_drop_down_menu.closeMenu()
//            chiZi_model = if (position == 0) types[0] else types[position]
//            Log.e("click0", chiZi_model)
            Toast.makeText(this, types[position], Toast.LENGTH_SHORT).show()
        }

        task_drop_down_menu
                .setDropDownMenu(Arrays.asList(*headers), popupViews)
    }


    fun default(view: View) {
        listAdapter.setCheckItem(0)
        task_drop_down_menu.closeMenu()
        task_drop_down_menu.setTabDefaultPositionText(types[0])

    }

    fun toTab(view: View) {
        val intent = Intent(this, MoreTabActivity::class.java)
        startActivity(intent)
    }
}
