package com.wordcon.client.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                .background(Color.Black)
        ) {
            Text(text = "Создание игры", color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            var roomName by remember { mutableStateOf("") }
            TextField(
                value = roomName,
                onValueChange = { roomName = it },
                label = { Text("Название комнаты") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    disabledTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            var password by remember { mutableStateOf("") }
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль (необязательно)") },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    disabledTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            var passwordConfirm by remember { mutableStateOf("") }
            TextField(
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                label = { Text("Пароль (необязательно)") },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    disabledTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Лимит игроков", color = Color.White)
            var playerLimit by remember { mutableStateOf(4f) }
            Text(text = playerLimit.toInt().toString(), color = Color.White)
            Slider(
                value = playerLimit,
                onValueChange = { playerLimit = it },
                valueRange = 1f..10f,
                steps = 8,
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.White,
                    inactiveTrackColor = Color.Gray
                )
            )

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
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Black,
                        disabledTextColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
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

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = { /* Handle create game */ },
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.Green, shape = RoundedCornerShape(28.dp))
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Create Game", tint = Color.White)
                }
            }
        }
    }
}

