package com.jefferymoju.coinapp.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jefferymoju.coinapp.presentation.Screen
import com.jefferymoju.coinapp.presentation.coin_list.component.CoinListItem
import com.jefferymoju.coinapp.presentation.coin_list.component.CoinListViewModel

@Composable // composable for the coinlist screen
fun CoinListScreen(
    // nav controller for navigation
    navController: NavController,
    // this will give us the viewmodel instance
    viewModel: CoinListViewModel = hiltViewModel()
) {
    // first we want to get a reference to the state
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){  // we get a Box that fills the screen
        LazyColumn(modifier = Modifier.fillMaxSize()){// we use a lazy column that fills the screen
            items(state.coins) { coin ->  // we use the item that gives us a list and pass our state.coins
                CoinListItem(        // for each single coin we can map that to a composable to our coin list item
                    coin = coin,
                    onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}") // we pass the coin.id to have access to the coin we want to load
                    } )
            }
        }
        if (state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}