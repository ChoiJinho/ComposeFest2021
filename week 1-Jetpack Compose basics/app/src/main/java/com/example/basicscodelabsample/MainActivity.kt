package com.example.basicscodelabsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelabsample.ui.theme.BasicsCodelabSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabSampleTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary, modifier = Modifier.padding(vertical = 4.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp)) {
            Text(text = "Hello,")
            Text(text = name)
        }

//        Text(text = "Hello $name!", modifier = Modifier.padding(all = 24.dp))
    }
}

@Preview(showBackground = true, name = "DefaultPreview", widthDp = 320)
@Composable
private fun DefaultPreview() {
    BasicsCodelabSampleTheme {
        MyApp()
    }
}

@Composable
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(all = 8.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}