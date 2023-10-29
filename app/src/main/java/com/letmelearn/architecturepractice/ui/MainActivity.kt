package com.letmelearn.architecturepractice.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.letmelearn.architecturepractice.R
import com.letmelearn.architecturepractice.ui.theme.ArchitecturePracticeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val searchLocationViewModel: SearchLocationViewModel by viewModels()
    private lateinit var tempC:EditText


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initCollectors()
        tempC = findViewById(R.id.tempText)
        findViewById<ComposeView>(R.id.compose_view).setContent {
            ArchitecturePracticeTheme {
                // A surface container using the 'background' color from the theme
                SearchLocationBar(
                    onSearch = { searchLocation(it) }
                )
            }
        }
    }

    private fun initCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchLocationViewModel.searchLocationUiState.collect { searchLocationUiState ->
                    Log.d(
                        "Aryan",
                        "searchLocation() called with: searchLocationUiState = ${searchLocationUiState.tempInC}"
                    )
                    updateUi(searchLocationUiState)
                }
            }
        }
    }

    private fun updateUi(searchLocationUiState: SearchLocationUiState) {
        tempC.setText(searchLocationUiState.tempInC.toString())
    }


    override fun onStart() {
        super.onStart()
        Log.d("Aryan", "onStart() called")
    }

    private fun searchLocation(location: String) {
        searchLocationViewModel.searchLocation(location)
    }


    override fun onResume() {
        super.onResume()
        Log.d("Aryan", "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Aryan", "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Aryan", "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Aryan", "onDestroy() called")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchLocationBar(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(text)
            // Hide the keyboard after submitting the search
            keyboardController?.hide()
            //or hide keyboard
            focusManager.clearFocus()
        })
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArchitecturePracticeTheme {
        Greeting("Android")
    }
}