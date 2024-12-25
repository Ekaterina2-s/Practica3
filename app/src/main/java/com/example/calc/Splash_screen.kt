package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Splash_screen : AppCompatActivity() {

    private val imageList = listOf(
        R.drawable.img,

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = SplashScreenAdapter(this, imageList)
        viewPager.adapter = adapter

        // Автоматическое переключение слайдов
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val currentItem = viewPager.currentItem
                viewPager.setCurrentItem((currentItem + 1) % imageList.size, true)
                handler.postDelayed(this, 1) // Скорость переключения в миллисекундах
            }
        }
        handler.postDelayed(runnable, 5)

        // Переход на MainActivity спустя 2000 мс
        val intent = Intent(this, MainActivity::class.java)
        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 2000)
    }

    class SplashScreenAdapter(activity: FragmentActivity, private val imageList: List<Int>) : FragmentStateAdapter(activity) {

        override fun getItemCount(): Int {
            return imageList.size
        }

        override fun createFragment(position: Int): Fragment {
            return Splash_screenFragment.newInstance(imageList[position])
        }
    }
}