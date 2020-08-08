package com.example.kotlintabswipe

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.kotlintabswipe.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    //タブアイコン
    val tabIcons = arrayOf(
        R.drawable.ic_action_home,
        R.drawable.ic_action_news,
        R.drawable.ic_action_more
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        //アイコンのセットに使う
        val tabs: TabLayout = findViewById(R.id.tabs)

        //タブで利用するFragmentのリストを作成
        val tabsFragments = arrayListOf(
            Tab1Fragment::class.java,
            Tab2Fragment::class.java,
            Tab3Fragment::class.java
        )

        //Adapterの生成
        val mTabsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, tabsFragments)
        // ViewPagerにAdapterを設定
        container.adapter = mTabsPagerAdapter
        //ViwePagerのリスナを設定
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> changeColor(Color.RED)
                    1 -> changeColor(Color.BLUE)
                    2 -> changeColor(Color.DKGRAY)
                }
            }
        })
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        //タブアイコンのセット
        setUpTabIcon()
    }
    //ステータスバー、アクションバー、タブの色変更
    private fun changeColor(color : Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color)
        }
        tabs.setBackgroundColor(color)
        toolbar.setBackgroundColor(color)
    }
    //タブアイコンのセット
    private fun setUpTabIcon(){
        tabs.getTabAt(0)?.setIcon(tabIcons[0])
        tabs.getTabAt(1)?.setIcon(tabIcons[1])
        tabs.getTabAt(2)?.setIcon(tabIcons[2])
    }

    //override fun onCreateOptionsMenu(menu: Menu): Boolean {
       // menuInflater.inflate(R.menu.menu_main, menu)
       // return true
   // }
   // override fun onOptionsItemSelected(item: MenuItem): Boolean {
      //  val id = item.itemId
      //  if (id == R.id.action_settings) {
       //     return true
      //  }
      //  return super.onOptionsItemSelected(item)
  //  }

}