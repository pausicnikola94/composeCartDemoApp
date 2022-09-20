package com.example.composecartdemo.feature_cart.presentation.common

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.example.composecartdemo.feature_cart.presentation.main.MainViewModel
import com.example.composecartdemo.feature_cart.presentation.navigation.Screen

@Composable
fun TopAppBar(navController: NavController, mainViewModel: MainViewModel){
    var canPop by remember {  mutableStateOf(false) }
    val mainUiState = mainViewModel.mainUiState.collectAsState()
    val showCartIcon = mainUiState.value.hasCheckoutOrder ?: false
    val isMainScreen =
        (navController.currentBackStackEntry?.destination?.route ?: Screen.MainScreen.route) == Screen.MainScreen.route
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
            canPop = controller.previousBackStackEntry != null
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    val navigationIcon: (@Composable () -> Unit)? =
        if (canPop) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        } else {
            null
        }
    TopAppBar(
        title = { Text(text = "ComposeCartDemoApp") },
        backgroundColor = MaterialTheme.colors.primary,
        navigationIcon = navigationIcon,
        actions = {
            if(showCartIcon && isMainScreen){
                IconButton(onClick = {
                    navController.navigate(Screen.ShoppingCartScreen.route)},
                modifier = Modifier.testTag("CartIcon")) {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shopping Cart")
                }
            }
        }
    )
}
