package com.mcuhq.coloretsApp.biblio


import com.mcuhq.coloretsApp.R
/**
 * Created by trank on 26/11/2018.
 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.biblio.*
/*


Activitat pantalla de la biblioteca. Té associat l’arxiu biblio.xml (Programat amb kotlin)
 */
class Biblio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.biblio)

        val fragmentAdapter = PageAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }
}