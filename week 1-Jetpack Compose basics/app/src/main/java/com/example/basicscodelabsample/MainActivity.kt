package com.example.basicscodelabsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


            val extraPadding by animateDpAsState(
                targetValue = if (expanded.value) 48.dp else 0.dp,
                /*animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )*/
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 1000,
                    easing = FastOutSlowInEasing
                )
            )
            val extraColorPadding by animateColorAsState(
                targetValue = Color.Red
            )

            Column(
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
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
private fun MyApp(names: List<String> = List(1000) { "$it" }) {
    var shouldShowOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldShowOnboarding) {
        OnboardingScreen(onClicked = { shouldShowOnboarding = false })
    } else {
        Greetings(names)
    }
}

@Composable
private fun Greetings(names: List<String>) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun OnboardingScreen(onClicked: () -> Unit) {
    // TODO: This state should be hoisted
    /*var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }*/

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, name = "OnboardingPreview")
@Composable
private fun OnboardingPreview() {
    BasicsCodelabSampleTheme {
        OnboardingScreen(onClicked = {})
    }
}