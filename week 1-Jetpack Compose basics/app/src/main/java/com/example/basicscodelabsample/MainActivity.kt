package com.example.basicscodelabsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
        Row(modifier = Modifier.padding(all = 24.dp)) {
            var isExpanded by remember {
                mutableStateOf(false)
            }

            try {
                (isExpanded as State<*>).value
            } catch (e: Exception) {
                e.printStackTrace()
            }

            var expanded = remember {
                mutableStateOf(false)
            }

            (expanded as State<*>).value


            val extraPadding = if (expanded.value) 48.dp else 0.dp

            Column(
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }

            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(text = if (!expanded.value) "Show more" else "Show less")
            }
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