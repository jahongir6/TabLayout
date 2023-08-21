package com.example.viewpagertablayout.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.tablayout.databinding.ItemPageBinding
import com.example.viewpagertablayout.models.User

class MyPagerAdapter(val list: List<User>) : PagerAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemPageBinding =
            ItemPageBinding.inflate(LayoutInflater.from(container.context), container, false)
        //Picasso.get().load(list[position].image).into(itemPageBinding.imageView)
        itemPageBinding.imageView.setImageResource(list[position].image)
        container.addView(itemPageBinding.root)
        return itemPageBinding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].text
    }
}