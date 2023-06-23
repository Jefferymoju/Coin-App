package com.jefferymoju.coinapp.presentation.coin_list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jefferymoju.coinapp.domain.model.Coin

@Composable     // composable for each coin item
fun CoinListItem(
    coin: Coin,  // parameter for the coin to display data of type Coin
    onItemClick: (Coin) -> Unit  // constructor function for onclick which gives us the coin as parameter
){
    Row(modifier = Modifier // create a row of text for the coinList with compose
        .fillMaxWidth()
        .clickable { onItemClick(coin) }   // set the item to be clickable
        .padding(20.dp),
        // arrange the texts horizontally and put a space between to make sure the two texts are pushed apart
    horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "${coin.rank}. ${coin.name} (${coin.symbol})",  // Text for the coin rank, name and symbol
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = if (coin.is_active) "active" else "inactive",  // Text for if the coin is active or not
            color = if (coin.is_active) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.align(CenterVertically)
            )
    }

}