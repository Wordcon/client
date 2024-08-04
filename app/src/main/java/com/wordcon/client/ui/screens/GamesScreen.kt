package com.wordcon.client.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wordcon.client.R
import com.wordcon.client.core.network.entities.GameInfo
import com.wordcon.client.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Wordcon",
                        fontWeight = FontWeight.SemiBold
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            GameList()

            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateGameScreen.route) {
                        popUpTo(Screen.GamesScreen.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                containerColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp, end = 16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}

@Composable
fun GameList() {
    val games = remember {
        arrayListOf(
            GameInfo("towns", 2, 4, "open", "1"),
            GameInfo("animals", 1, 4, "open", "2"),
            GameInfo("towns", 3, 4, "open", "3"),
            GameInfo("food", 4, 4, "open", "4"),
            GameInfo("animals", 2, 4, "open", "5")
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(games) { game ->
            GameCard(
                type = game.type,
                playersIn = game.playersIn,
                maxPlayers = game.maxPlayers,
                status = game.status,
                id = game.id
            )
        }
    }
}

@Composable
fun GameCard(type: String, playersIn: Int, maxPlayers: Int, status: String, id: String) {
    val backgroundColor = when(status) {
        "open" -> Color.Green.copy(alpha = 0.2f)
        "private" -> Color(0xFFFFA500).copy(alpha = 0.2f)
        else -> Color.Red.copy(alpha = 0.6f)
    }

    val borderColor = when(status) {
        "open" -> Color.Green
        "private" -> Color(0xFFFFA500)
        else -> Color.Red
    }

    val image = when(type) {
        "towns" -> R.drawable.moscow
        "food" -> R.drawable.food
        "animals" -> R.drawable.capybara
        else -> R.drawable.turtle
    }

    val status_lang = when(status) {
        "open" -> stringResource(R.string.open)
        "private" -> stringResource(R.string.private_)
        else -> stringResource(R.string.full)
    }

    val type_lang = when(type) {
        "cities" -> stringResource(R.string.cities)
        "animals" -> stringResource(id = R.string.animals)
        "food" -> stringResource(id = R.string.food)
        else -> "robot"
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
        ),
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(95.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(95.dp)
                    .padding(10.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .height(85.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = type_lang, style = MaterialTheme.typography.headlineSmall)
                Text(text = "ID: $id", style = MaterialTheme.typography.bodyMedium)
                Text(text = stringResource(R.string.players, playersIn, maxPlayers), style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(backgroundColor)
                    .border(1.dp, borderColor, shape = RoundedCornerShape(20.dp))
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp, vertical = 6.dp),
            ) {
                Text(text = status_lang, color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}