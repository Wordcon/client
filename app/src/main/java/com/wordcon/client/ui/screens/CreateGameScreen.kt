package com.wordcon.client.ui.screens

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
            Text(text = "Создание игры", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            RoomNameInput()

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInput()

            Spacer(modifier = Modifier.height(16.dp))

            PlayersLimitSlider()

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Режим", color = Color.White)
            var expanded by remember { mutableStateOf(false) }
            var selectedMode by remember { mutableStateOf("Города") }
            Box {
                TextField(
                    value = selectedMode,
                    onValueChange = {},
                    label = { Text("Режим") },
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expanded = true
                        }
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
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White)
                ) {
                    listOf("Города", "Страны", "Животные").forEach { mode ->
                        DropdownMenuItem(
                            onClick = {
                                selectedMode = mode
                                expanded = false
                            },
                            text = { Text(mode) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(46.dp))

            FloatingActionButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                containerColor = Color.Green,
                contentColor = Color.Black
            ) {
                Icon(Icons.Filled.Check, "Floating action button.")
            }
        }
    }
}

@Composable
fun PlayersLimitSlider() {
    Text(text = "Лимит игроков", color = Color.White)
    var playerLimit by rememberSaveable { mutableStateOf(4f) }
    Text(text = playerLimit.toInt().toString(), color = Color.White)
    Slider(
        value = playerLimit,
        onValueChange = { playerLimit = it },
        valueRange = 1f..100f,
        steps = 100,
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.White,
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



