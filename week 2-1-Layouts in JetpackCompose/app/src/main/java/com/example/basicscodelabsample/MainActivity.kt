package com.example.basicscodelabsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelabsample.ui.theme.BasicsCodelabSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabSampleTheme {
                /*// A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }*/

                PhotographerCard()
                LayoutsCodelab()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicsCodelabSampleTheme {
        Greeting("Android")
    }
}

@Composable
fun PhotographerCard() {
    Row(
        modifier = Modifier
            .padding(all = 48.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .clickable(onClick = {})
            .padding(all = 16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // Image goes here
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(alignment = CenterVertically)
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(values = arrayOf(LocalContentAlpha provides ContentAlpha.medium)) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

const val Blue = 0xFF0000FF

//@Preview(showBackground = true, backgroundColor = Blue)
@Composable
fun PhotographerCardPreview() {
    BasicsCodelabSampleTheme {
        PhotographerCard()
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LayoutsCodelab",
//                    style = MaterialTheme.typography.h3
                    )
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Face, contentDescription = null)
                    }
                }
            )
        }) {
        BodyContent(
            Modifier
                .padding(paddingValues = it)
                .then(other = Modifier)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutsCodelabPreview() {
    BasicsCodelabSampleTheme {
        LayoutsCodelab()
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}











