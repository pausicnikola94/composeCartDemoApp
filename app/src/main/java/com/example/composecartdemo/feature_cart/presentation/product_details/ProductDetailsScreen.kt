package com.example.composecartdemo.feature_cart.presentation.product_details

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.composecartdemo.feature_cart.presentation.common.CartCard
import com.example.viewdemoapp.feature_cart.presentation.models.ProductDetails
import com.example.viewdemoapp.feature_cart.presentation.models.toProductDetails
import com.example.viewdemoapp.feature_cart.presentation.product_details.ProductDetailsViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductDetailsScreen(
    productDetailsViewModel: ProductDetailsViewModel,
    result: ProductDetails?
) {
    if(result != null){
        LaunchedEffect(Unit){
            productDetailsViewModel.setup(result)
        }
    }

    val productDetailsUiState = productDetailsViewModel.productDetailsUiState.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxHeight().fillMaxWidth()
    ) {
        GlideImage(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp),
            imageModel = productDetailsUiState.value.product?.image,
            imageOptions = ImageOptions(
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center)
        )
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxHeight()
    ) {
        CartCard(
            null,
            productDetailsUiState.value.product?.toProductDetails(),
            {   productDetailsUiState.value.product?.toProductDetails()
                ?.let { productDetailsViewModel.addToCart(it) } },
            {   productDetailsUiState.value.product?.toProductDetails()
                ?.let { productDetailsViewModel.removeFromCart(it) } }
        )
    }
}