package uz.salimovdeveloper.chatapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.salimovdeveloper.chatapp.R
import uz.salimovdeveloper.chatapp.databinding.FragmentSignUpBinding
import uz.salimovdeveloper.chatapp.models.MyNumber

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)

        binding.btnNext.setOnClickListener {
            val number = binding.number.text.toString().trim()

            val number2 = number.substring(3)

            if (number2!="" && number.length == 13){

                MyNumber.number = binding.number.text.toString().trim()
//                findNavController().navigate(R.id.codeFragment)

            }else{
                Toast.makeText(
                    requireContext(),
                    "Iltimos raqamingizni to'liq kiriting",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }
}