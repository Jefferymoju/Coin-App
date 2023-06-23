package com.jefferymoju.coinapp.presentation

// sealed class that helps us deal with the route of navigation
sealed class Screen(val route: String){
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}

