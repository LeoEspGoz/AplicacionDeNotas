package com.example.appnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appnotas.ui.theme.AppNotasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun MyContent() {
    Column(
        modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        SmalledBtn()
        Spacer(modifier = Modifier.height(8.dp))
        ExtendedBtn()
    }

    }


@Composable
fun ExtendedBtn(modifier: Modifier = Modifier) {
    ExtendedFloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Text(text = "add something")
        Icon(imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint= Color.White)

    }

}
@Composable
fun SmalledBtn(modifier: Modifier = Modifier) {
    SmallFloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Icon(imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint= Color.White)

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppNotasTheme {
        MyContent()
    }
}