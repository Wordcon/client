package com.wordcon.client.ui.screens

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wordcon.client.R
import com.wordcon.client.core.network.entities.GameMode
import com.wordcon.client.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGameScreen(navController: NavController) {
    var playersLimit by rememberSaveable { mutableIntStateOf(2) }
    var roomName by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.create_game)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = if (roomName.isNotBlank()) {
                    { navController.navigate("${Screen.LobbyScreen.route}/$roomName") }
                } else {
                    { null }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CreateGameFormSection(titleRes = R.string.room_info) {
                RoomNameTextField(roomName) { newRoomName ->
                    roomName = newRoomName
                }
                RoomPasswordTextField()
            }
            GameModeSection(titleRes = R.string.mode) {
                GameModeDropdownMenu()
            }
            PlayersLimitSection(titleRes = R.string.players_limit, playersLimit = playersLimit) {
                PlayersLimitSlider(playersLimit) { newLimit ->
                    playersLimit = newLimit
                }
            }
        }
    }
}

@Composable
fun CreateGameFormSection(
    @StringRes titleRes: Int,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 12.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun RoomNameTextField(
    roomName: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = roomName,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.room_name)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            ),
        colors = colors(
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
fun RoomPasswordTextField() {
    var roomPassword by rememberSaveable { mutableStateOf("") }
    var showPassword by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = roomPassword,
        onValueChange = { roomPassword = it },
        label = { Text(stringResource(R.string.room_password)) },
        visualTransformation = if (showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            IconButton(onClick = { showPassword = !showPassword }) {
                Icon(
                    painter = painterResource(
                        id = if (showPassword) {
                            R.drawable.visibility_24px
                        } else {
                            R.drawable.visibility_off_24px
                        }
                    ),
                    contentDescription = "Toggle password visibility"
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.medium
            ),
        colors = colors(
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
fun GameModeSection(
    @StringRes titleRes: Int,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = stringResource(id = titleRes),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameModeDropdownMenu() {
    val categoriesList = listOf(
        GameMode(
            id = "cities",
            title = stringResource(id = R.string.cities),
            image = R.drawable.apartment_24px
        ),
        GameMode(
            id = "food",
            title = stringResource(id = R.string.food),
            image = R.drawable.restaurant_24px
        ),
        GameMode(
            id = "animals",
            title = stringResource(id = R.string.animals),
            image = R.drawable.pets_24px
        )
    )

    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedCategory by rememberSaveable { mutableStateOf(categoriesList[0].title) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }
    ) {
        TextField(
            value = selectedCategory,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = if (isExpanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = null
                )
            },
            colors = colors(
                focusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                cursorColor = Color.White,
                disabledTextColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .menuAnchor()
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = MaterialTheme.shapes.medium
                ),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categoriesList.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategory = category.title
                        isExpanded = false
                    },
                    text = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = category.image),
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                text = category.title,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PlayersLimitSection(
    @StringRes titleRes: Int,
    playersLimit: Int,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            text = "${stringResource(id = titleRes)}: $playersLimit",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun PlayersLimitSlider(
    playersLimit: Int,
    onValueChange: (Int) -> Unit
) {
    Slider(
        value = playersLimit.toFloat(),
        onValueChange = { onValueChange(it.toInt()) },
        valueRange = 2f..50f,
        steps = 49,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    )
}