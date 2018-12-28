package com.lhm.dropdownmenuex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import dropdownmenu.adapter.ListDropDownAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.R.string.ok
import android.widget.TextView
import java.util.Arrays.asList
import dropdownmenu.adapter.ConstellationAdapter
import android.widget.GridView
import dropdownmenu.adapter.GirdDropDownAdapter
import kotlinx.android.synthetic.main.activity_more_tab.*
import java.util.Arrays.asList
import com.lhm.dropdownmenuex.R.id.constellation




class MoreTabActivity : AppCompatActivity() {

    private val types = arrayOf("未完成", "已完成", "我收到的", "我发出的").toList()
    private lateinit var listAdapter: ListDropDownAdapter

    private val car = arrayOf("宝马", "奔驰", "奥迪", "特斯拉").toList()
    private val headers = arrayOf("未完成", "车", "房","星座")
    private lateinit var listCarAdapter: ListDropDownAdapter


    private val citys = arrayOf("不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州").toList()
    private val constellations = arrayOf("不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座").toList()


    private lateinit var cityAdapter: GirdDropDownAdapter
    private lateinit var ageAdapter: ListDropDownAdapter
    private lateinit var constellationAdapter: ConstellationAdapter

    private var constellationPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_tab)

        initView()
    }


    private fun initView() {
        val listView = ListView(this)
        listView.dividerHeight = 0
        listAdapter = ListDropDownAdapter(this, types)
        listView.adapter = listAdapter

        val carListView = ListView(this)
        carListView.dividerHeight = 0
        listCarAdapter = ListDropDownAdapter(this, car)
        carListView.adapter = listCarAdapter

        //init city menu
        val cityView = ListView(this)
        cityAdapter = GirdDropDownAdapter(this, citys)
        cityView.dividerHeight = 0
        cityView.adapter = cityAdapter


        //init constellation
        val constellationView = layoutInflater.inflate(R.layout.custom_layout, null)
        val constellation = constellationView.findViewById<GridView>(R.id.constellation)
        constellationAdapter = ConstellationAdapter(this, constellations)
        constellation.adapter = constellationAdapter

        val ok = constellationView.findViewById<TextView>(R.id.ok)
        ok.setOnClickListener(View.OnClickListener {
            tab_drop_down_menu.setTabText(if (constellationPosition === 0) headers[3] else constellations[constellationPosition])
            tab_drop_down_menu.closeMenu()
        })


        val popupViews = ArrayList<View>()
        //init popupViews
        popupViews.add(listView)
        popupViews.add(carListView)
        popupViews.add(cityView)
        popupViews.add(constellationView)


        //add item click event

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            listAdapter.setCheckItem(position)
            tab_drop_down_menu.setTabText(if (position == 0) headers[0] else types[position])
            tab_drop_down_menu.closeMenu()
            Toast.makeText(this, types[position], Toast.LENGTH_SHORT).show()
        }

        carListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            listCarAdapter.setCheckItem(position)
            tab_drop_down_menu.setTabText(if (position == 0) headers[1] else car[position])
            tab_drop_down_menu.closeMenu()
            Toast.makeText(this, car[position], Toast.LENGTH_SHORT).show()
        }


        cityView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            cityAdapter.setCheckItem(position)
            tab_drop_down_menu.setTabText(if (position == 0) headers[2] else citys[position])
            tab_drop_down_menu.closeMenu()

            Toast.makeText(this, citys[position], Toast.LENGTH_SHORT).show()
        }
        constellation.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            constellationAdapter.setCheckItem(position)
            constellationPosition = position
        }


        tab_drop_down_menu
                .setDropDownMenu(Arrays.asList(*headers), popupViews)
    }


    override fun onBackPressed() {
        //退出activity前关闭菜单
        if (tab_drop_down_menu.isShowing) {
            tab_drop_down_menu.closeMenu()
        } else {
            super.onBackPressed()
        }
    }
}
