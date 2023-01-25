package com.sara.canwesail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sara.canwesail.ui.theme.CanWeSailTheme
import com.sara.canwesail.view.appNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myApp {
                appNavigation()
            }
        }
    }
}

@Composable
fun myApp (content: @Composable () -> Unit) {
    CanWeSailTheme {
        content()
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    myApp {
        appNavigation()
    }
}