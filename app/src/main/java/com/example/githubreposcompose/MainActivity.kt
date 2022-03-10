package com.example.githubreposcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubreposcompose.ui.theme.GithubReposComposeTheme
import com.example.githubreposcompose.ui.theme.Purple200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubReposComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //Greeting(Message("Android", "Jetpack Compose"))
                    //Conversation(SampleData.conversationSample)
                    TodoItemInput{
                        Log.d("text",""+it)
                    }
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Greeting(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "dummy picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, Purple200, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        var isExpanded by remember {
            mutableStateOf(false)
        }
        Column(modifier = Modifier
            .weight(1f)
            .clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = Purple200,
                style = MaterialTheme.typography.subtitle1
            )
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
        IconButton(onClick = { isExpanded = !isExpanded }){
            Icon(imageVector = if(isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if(isExpanded) "show less" else "show more")
            
        }
    }
}

@Composable
fun Conversation(conversationSample: List<Message>) {
    LazyColumn {
        items(conversationSample) { message ->
            Greeting(msg = message)
        }
    }

}

@Composable
fun TodoItemInput(onItemComplete: (String) -> Unit) {
    val (text, setText) = rememberSaveable { mutableStateOf("") }

    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            InputTextField(
                text = text,
                onTextChange = setText,
                Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            TextButton(
                onClick = { onItemComplete(text) },
                modifier = Modifier.align(Alignment.CenterVertically)){
                    Text(text = "Add")
                }
        }
    }
}

@Composable
fun InputTextField(text : String, onTextChange : (String) -> Unit, modifier: Modifier){
    TextField(value = text,
        onValueChange = onTextChange,
        modifier = modifier.fillMaxWidth()
    )



}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GithubReposComposeTheme {
        Greeting(Message("Android", "Jetpack Compose"))
    }
}