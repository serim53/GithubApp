package com.example.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.githubapp.databinding.ActivityMainBinding
import com.example.githubapp.presentation.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = ViewPagerAdapter(this)

        binding.viewpager.registerOnPageChangeCallback(
            object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNavigation.menu.getItem(position).isChecked = true
                }
            }
        )

        binding.bottomNavigation.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.item_search -> {
                        binding.viewpager.currentItem = 0
                        true
                    }
                    R.id.item_favorite -> {
                        binding.viewpager.currentItem = 1
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}