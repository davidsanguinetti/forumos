package com.example.forumos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.forumos.databinding.ActivityNameBinding

class ActivityName : AppCompatActivity() {

    private val viewModel: ActivityNameViewModel by viewModels()
    private var _binding: ActivityNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityNameBinding.inflate(layoutInflater)

        setContentView(_binding!!.root)

        _binding?.etName?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setName(s.toString())

                (application as AppForumos).userName = s.toString()

                _binding!!.btcontinuar.isEnabled = !s.toString().isEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        _binding!!.btcontinuar.setOnClickListener {
            val intent = Intent(this@ActivityName, MainActivity::class.java)
            startActivity(intent)
        }


    }

}