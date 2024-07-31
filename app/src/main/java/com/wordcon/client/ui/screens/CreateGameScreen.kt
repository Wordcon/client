package com.wordcon.client.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wordcon.client.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.go_back)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            Text(text = stringResource(R.string.creating_game))

            Spacer(modifier = Modifier.height(16.dp))

            RoomNameInput()

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInput()

            Spacer(modifier = Modifier.height(16.dp))

            PlayersLimitSlider()

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(R.string.mode))

            Spacer(modifier = Modifier.height(16.dp))

            GameMode()

            Spacer(modifier = Modifier.height(46.dp))

            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                containerColor = Color(0xFF17BD13),
                contentColor = Color.Black
            ) {
                Icon(Icons.Filled.Check, "Floating action button.")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameMode() {
    var expanded by remember { mutableStateOf(false) }
    var selectedMode by remember { mutableStateOf("Города") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedMode,
            onValueChange = {},
            label = { Text("Режим") },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = MaterialTheme.shapes.medium
                ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                cursorColor = Color.White,
                disabledTextColor = Color.White
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            listOf(Mode("Города", R.drawable.skyscraper), Mode("Еда", R.drawable.apple), Mode("Животные", R.drawable.turtle)).forEach { mode ->
                DropdownMenuItem(
                    onClick = {
                        selectedMode = mode.title
                        expanded = false
                    },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = mode.image),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(mode.title)
                        }
                    }
                )
            }
        }
    }
}

data class Mode(val title: String, val image: Int)


@Composable
fun PlayersLimitSlider() {
    Text(text = "Лимит игроков")
    var playerLimit by rememberSaveable { mutableStateOf(4f) }
    Text(text = playerLimit.toInt().toString())
    Slider(
        value = playerLimit,
        onValueChange = { playerLimit = it },
        valueRange = 1f..100f,
        steps = 100,
        colors = SliderDefaults.colors(
            inactiveTrackColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput() {
    var password by rememberSaveable { mutableStateOf("") }
    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Пароль (необязательно)") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = Color.White,
            disabledTextColor = Color.White
        )
    )
}

@Composable
fun RoomNameInput() {
    var roomName by rememberSaveable { mutableStateOf("") }
    TextField(
        value = roomName,
        onValueChange = { roomName = it },
        label = { Text("Название комнаты") },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = Color.White,
            disabledTextColor = Color.White
        )
    )
}



