package uz.salimovdeveloper.chatapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import uz.salimovdeveloper.chatapp.R
import uz.salimovdeveloper.chatapp.databinding.FragmentAddUserBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class AddUserFragment : Fragment() {
    private lateinit var binding: FragmentAddUserBinding
    private lateinit var uriPath: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)

        uriPath = ""

        binding.imageAdd.setOnClickListener {
            getImageContent.launch("image/*")
        }

        return binding.root
    }
    val getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()) {
        it ?: return@registerForActivityResult
        binding.imageAdd.setImageURI(it)
        val inputStream = requireActivity().contentResolver.openInputStream(it)
        val title = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val file = File(requireActivity().filesDir, "$title.jpg")
        val fileOutputStream = FileOutputStream(file)
        inputStream?.copyTo(fileOutputStream)
        inputStream?.close()
        fileOutputStream.close()
        uriPath = file.absolutePath
    }
}