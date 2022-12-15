package uz.salimovdeveloper.chatapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.salimovdeveloper.chatapp.R
import uz.salimovdeveloper.chatapp.databinding.FragmentHeaderBinding

class HeaderFragment : Fragment() {
    private lateinit var binding: FragmentHeaderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeaderBinding.inflate(layoutInflater)

        return binding.root
    }
}