package araya.gonzalo.alkewallwt.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import araya.gonzalo.alkewallwt.databinding.ActivityProfilePageBinding

class ProfilePageActivity : AppCompatActivity() {
    lateinit var binding : ActivityProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}