package com.example.composecartdemo.feature_cart.presentation.shopping_cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.example.composecartdemo.feature_cart.presentation.common.CartCard
import com.example.composecartdemo.feature_cart.presentation.common.ProductItemImage
import com.example.composecartdemo.feature_cart.presentation.navigation.Screen
import com.example.viewdemoapp.feature_cart.presentation.models.ProductWrapper
import com.example.viewdemoapp.feature_cart.presentation.shopping_cart.ShoppingCartViewModel

@Composable
fun ShoppingCartScreen(
    navController: NavController,
    viewModel: ShoppingCartViewModel
) {
    val shoppingCartResult = viewModel.shoppingCartUiState.collectAsState()
    //na null je false jer default value flowa donese null 
    val isEmpty =  shoppingCartResult.value.products?.isEmpty() ?: false

    if(isEmpty){
        if(navController.currentBackStackEntry?.destination?.route != Screen.MainScreen.route) {
            navController.popBackStack()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().testTag("ShoppingCartColumn")
    ) {
        if(shoppingCartResult.value.products == null) return@LazyColumn
        items(shoppingCartResult.value.products!!) { photo ->
            ProductItemContainer(photo, navController, viewModel)
        }
    }
}

@Composable
private fun ProductItemContainer(
    productWrapper: ProductWrapper,
    navController: NavController,
    viewModel: ShoppingCartViewModel
){
    Surface {
        Row(
            horizontalArrangement  =  Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()) {
            ProductItemImage(
                width = 150,
                height = 150,
                padding = 15,
                productDetails = productWrapper.product,
                navController = navController
            )
            CartCard(
                200,
                productWrapper.product,
                { viewModel.addToCart(it) },
                { viewModel.removeFromCart(it) }
            )
        }
    }

}