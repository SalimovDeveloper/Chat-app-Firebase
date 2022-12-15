package uz.salimovdeveloper.chatapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import uz.salimovdeveloper.chatapp.R
import uz.salimovdeveloper.chatapp.databinding.FragmentCodeBinding
import uz.salimovdeveloper.chatapp.models.MyNumber
import java.util.concurrent.TimeUnit

class CodeFragment : Fragment() {
    private lateinit var binding: FragmentCodeBinding
    lateinit var auth: FirebaseAuth
    lateinit var storeVerificationId:String
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeBinding.inflate(layoutInflater)

        binding.nemberInfo.text = MyNumber.number

        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("uz")

        var number = MyNumber.number

        setVerificationCode(number!!)

        binding.number.addTextChangedListener {
            verifiyCode()
        }
        return binding.root
    }
    private fun verifiyCode() {
        val code = binding.number.text.toString()
        if (code.length == 6){
            val credential = PhoneAuthProvider.getCredential(storeVerificationId, code)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun setVerificationCode(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//            Log.d(TAG, "onVerificationCompleted: $credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

            if (e is FirebaseAuthInvalidCredentialsException){

            }else if (e is FirebaseTooManyRequestsException){

            }

        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
//            Log.d(TAG, "onCodeSent: $verificationId")

            storeVerificationId = verificationId
            resendToken = token
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential){
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")

                    Toast.makeText(requireContext(), "Muvaffaqiyatli", Toast.LENGTH_SHORT).show()
//                    binding.addPassword.text.clear()
//                    findNavController().navigate(R.id.addUserFragment)

//                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(requireContext(), "Muvaffaqiyatsiz!!!", Toast.LENGTH_SHORT).show()
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(requireContext(), "Kod xato kiritildi", Toast.LENGTH_SHORT).show()
                    }
                    // Update UI
                }
            }
    }
}