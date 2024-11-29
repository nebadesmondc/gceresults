package com.dezzy.gceresults

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dezzy.gceresults.ui.theme.GceresultsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            GceresultsTheme {
                GceResultsApp()
            }
        }
    }
}
