package com.example.composecartdemo.feature_cart.presentation.category_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composecartdemo.feature_cart.presentation.common.CartCard
import com.example.composecartdemo.feature_cart.presentation.common.ProductItemImage
import com.example.viewdemoapp.feature_cart.presentation.category_details.CategoryDetailsViewModel
import com.example.viewdemoapp.feature_cart.presentation.models.ProductWrapper

@Composable
fun CategoryDetailsScreen(
    navController: NavController,
    viewModel: CategoryDetailsViewModel
) {
    val id = navController.currentBackStackEntry?.arguments?.get("categoryId")
    if(id != null){
        LaunchedEffect(Unit){
            viewModel.setup(id.toString())
        }
    }
    val categoryUiState = viewModel.categoryUiState.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier
            .testTag("CategoryDetailsColumn")
    ) {
        if(categoryUiState.value.products == null) return@LazyVerticalGrid
        items(categoryUiState.value.products!!) { photo ->
            ProductItemContainer(photo, navController, viewModel)
        }
    }
}

@Composable
private fun ProductItemContainer(
    productWrapper: ProductWrapper,
    navController: NavController,
    viewModel: CategoryDetailsViewModel
){
    Surface(
        Modifier
            .width(150.dp)
            .padding(5.dp)
    ) {
        Column {
            ProductItemImage(
                width = null,
                height = 200,
                padding = null,
                productDetails = productWrapper.product,
                navController = navController
            )
            CartCard(
                null,
                productWrapper.product,
                { viewModel.addToCart(it) },
                { viewModel.removeFromCart(it) }
            )
        }
    }
}