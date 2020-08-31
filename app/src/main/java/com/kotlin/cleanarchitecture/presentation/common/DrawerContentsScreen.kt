package com.kotlin.cleanarchitecture.presentation.common

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kotlin.cleanarchitecture.state.PokeDelegate
import com.kotlin.cleanarchitecture.state.PokeDelegate.screenNumber
import com.kotlin.project.data.model.AppScreen

@Composable
fun DrawerContentsScreen(scaffoldState: ScaffoldState) {
    val sNumber by PokeDelegate.screenNumber.observeAsState()
    Column {
        Button(
            modifier = Modifier.gravity(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 16.dp),
            onClick = { scaffoldState.drawerState.close() },
            content = { Text("Close Drawer") }
        )
        AppScreen.values().mapIndexed { index, list ->
            val color = if (sNumber == index) {
                Pair(Color.Gray, Color.White)
            } else {
                Pair(Color.White, Color.Gray)
            }
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(8.dp) +
                    Modifier.fillMaxWidth() +
                    Modifier.preferredHeight(60.dp) +
                    Modifier.clickable(
                        onClick = {
                            scaffoldState.drawerState.close()
                            screenNumber.postValue(index)
                        }
                    ),
                contentColor = color.second,
                backgroundColor = color.first,
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalGravity = Alignment.CenterHorizontally
                    ) {
                        Text(text = list.displayNameString)
                    }
                }
            )
        }
    }
}
