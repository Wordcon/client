package com.wordcon.client.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wordcon.client.R
import com.wordcon.client.core.network.entities.MessageSender
import com.wordcon.client.core.network.entities.Participant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(
    lobbyId: String,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.go_back)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions =  {
                    IconButton(onClick = {  }) {
                        Icon(painter = painterResource(id = R.drawable.menu_book_24px), contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets(top = 10.dp))
        ) {
            val participants = arrayListOf(
                Participant(1, "Вы", R.drawable.moscow),
                Participant(2, "не гений", R.drawable.food),
                Participant(3, "гений", R.drawable.moscow),
                Participant(4, "nickname", R.drawable.food)
            )
            Spacer(modifier = Modifier.height(4.dp))
            AllParticipantsList(participants)
            CurrentChat(
                messages = arrayListOf(
                    MessageSender("ку", "Алиса"),
                    MessageSender("привет", "Боб"),
                    MessageSender("как дела?", "Чарли")
                ),
                modifier = Modifier
                    .weight(15f)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            MessageInput()
        }
    }
}

@Composable
fun CurrentChat(messages: ArrayList<MessageSender>, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            reverseLayout = true,
        ) {
            items(messages) { message ->
                MessageItem(messageText = message.messageText, username = message.username)
            }
        }
    }
}

@Composable
fun MessageItem(
    username: String,
    messageText: String,
) {
    Card(
        modifier = Modifier
            .padding(start = 26.dp, top = 6.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .border(1.dp, Color.White, shape = MaterialTheme.shapes.medium)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(horizontal = 5.dp, vertical = 5.dp)
        ) {
            Text(text = username, color = Color.Green)
            Text(text = messageText)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput() {
    var text by rememberSaveable { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(Color.Black)
            .padding(horizontal = 8.dp, vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {  }) {
            Icon(painter = painterResource(id = R.drawable.chat_24px), contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
        TextField(
            value = text,
            onValueChange = {text = it},
            placeholder = { Text(stringResource(R.string.type_a_word)) },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        IconButton(onClick = {  }) {
            Icon(Icons.Default.Send, contentDescription = "Send", tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
fun AllParticipantsList(participants: ArrayList<Participant>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 26.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(participants) { participant ->
            ParticipantCard(participant)
        }
    }
}

@Composable
fun ParticipantCard(participant: Participant) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.moscow),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .border(
                    2.dp,
                    MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
        )
        Text(text = participant.name, color = Color.White)
    }
}

//@Composable
//fun UsedWords() {
//    Card(
//        shape = MaterialTheme.shapes.medium,
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
//        ),
//        modifier = Modifier
//            .padding(horizontal = 26.dp)
//            .fillMaxWidth()
//            .height(40.dp)
//    ) {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Text(text = stringResource(R.string.used_words))
//            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Back")
//        }
//    }
//}
