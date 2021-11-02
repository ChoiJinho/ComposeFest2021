package com.example.basicscodelabsample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelabsample.ui.theme.BasicsCodelabSampleTheme

class MainActivityTutorial : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }

                Text(text = "Hello World!")

                MessageCard(Message("Android", "Jetpack Compose"))

                Conversation(messages = SampleData.conversationSample)
            }
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    private fun MessageCard(msg: Message) {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_delete),
                contentDescription = "Contact Profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember {
                mutableStateOf(false)
            }
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor: Color by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2,
                )

                Spacer(modifier = Modifier.height(4.dp))

                androidx.compose.material.Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 5.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier
//                        .animateContentSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }

        /*Row {
            Image(
                painter = painterResource(id = android.R.drawable.ic_delete),
//                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Contact Profile picture"
            )

            Column {
                Text(text = msg.author)
                Text(text = msg.body)
            }
        }*/
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn() {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        BasicsCodelabSampleTheme {
            Conversation(SampleData.conversationSample)
        }
    }


    @Preview(name = "Light Mode")
    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        name = "Drak Mode"
    )
    @Composable
    private fun PreviewMessageCard() {
        BasicsCodelabSampleTheme() {
            MessageCard(
                msg = Message(
                    author = "Colleague",
                    body = "Hey, take a look at Jetpack Compose, it's great!"
                )
            )
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

//@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    BasicsCodelabSampleTheme {
        Greeting("Android")
//        Text("Hello Worlds!")
    }
}