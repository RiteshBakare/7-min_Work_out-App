package bakareritesh1729atgmail.com.a7minutesworkoutapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import bakareritesh1729atgmail.com.a7minutesworkoutapp.databinding.ActivityMyFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MyFinishActivity : AppCompatActivity() {

    var binding: ActivityMyFinishBinding? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.FinishActionBar)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.FinishActionBar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }


        binding!!.btnFinish.setOnClickListener {
            finish()
        }

        val dao = (application as WorkOutApp).db.historyDao()
        addDateToDatabase(dao)

    }


    private fun addDateToDatabase(historyDao: HistoryDao) {

        val myCalendar = Calendar.getInstance()
        val dateTime = myCalendar.time

        Log.e("my Date: ", "" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss a", Locale.getDefault())
        val date = sdf.format(dateTime)

        Log.e("Formatted Date", "" + date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date = date))
            Log.e("Date ", "Added..!")
        }

    }

}