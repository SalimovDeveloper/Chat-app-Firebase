package uz.salimovdeveloper.chatapp.fragments.adapters

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.salimovdeveloper.chatapp.fragments.ChatsFragment
import uz.salimovdeveloper.chatapp.fragments.GroupsFragment

class MyFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ChatsFragment()
            }
            1 -> {
                GroupsFragment()
            }
            else ->{throw Resources.NotFoundException("Boshqa Fragment yoq")}
        }
    }
}