package com.example.basicscodelabsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.basicscodelabsample.ui.theme.BasicsCodelabSampleTheme
import kotlinx.coroutines.launch

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
    /*Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }*/
    MyOwnColumn(modifier.padding(8.dp)) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Composable
fun SimpleList() {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
//    val scrollState = rememberScrollState()

    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(count = listSize) {
//            Text(text = "Item #$it")
                ImageListItem(index = it)
            }
        }
    }

}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

//@Preview(showBackground = true)
@Composable
fun SimpleListPreview() {
    BasicsCodelabSampleTheme {
        SimpleList()
    }
}

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(width = placeable.width, height = height) {
            // Where the composable gets placed
            placeable.placeRelative(x = 0, y = placeableY)
        }
    }
)

//@Preview(showBackground = true, name = "PaddingToBaseline")
@Composable
fun TextWithPaddingToBaselinePreview() {
    BasicsCodelabSampleTheme {
        Text("Hi there!", Modifier.firstBaselineToTop(32.dp))
    }
}

//@Preview(showBackground = true, name = "NormalPadding")
@Composable
fun TextWithNormalPaddingPreview() {
    BasicsCodelabSampleTheme {
        Text("Hi there!", Modifier.padding(top = 32.dp))
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    // custom layout attributes
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = MeasurePolicy { measurables, constraints ->
            // Don't constrain child views further, measure them with given constraints
            // List of measured children
            val placeables = measurables.map { measurable ->
                // Measure each child
                measurable.measure(constraints)
            }

            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Set the size of the layout as big as it can
            layout(width = constraints.maxWidth, height = constraints.maxHeight) {
                // Place children in the parent layout
                placeables.forEach { placeable ->
                    // Position item on the screen
                    placeable.placeRelative(x = 0, y = yPosition)

                    // Record the y co-ord placed up to
                    yPosition += placeable.height
                }
            }
        })
}






