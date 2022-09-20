package com.example.composecartdemo.feature_cart.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composecartdemo.feature_cart.presentation.navigation.Screen
import com.example.viewdemoapp.feature_cart.presentation.models.ProductDetails
import com.example.viewdemoapp.feature_cart.presentation.models.ProductWrapper
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductItemImage(width: Int?, height: Int, padding: Int?, productDetails: ProductDetails, navController: NavController?) {
    var modifier = Modifier
        .height(height.dp)
        .clickable {
            navController?.navigateCustom(
                Screen.ProductDetailsScreen,
                productDetails)
        }

    if(width != null){
        modifier = modifier.then(Modifier.width(width.dp))
    }

    if(padding != null){
        modifier = modifier.then(Modifier.padding(padding.dp))
    }

    GlideImage(
        modifier = modifier,
        imageModel = productDetails.image,
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun ProductItemImagePreview(){
    ProductItemImage(0, 0, 0, productDetails =
  ProductDetails("bla", "", "",
        "", 0, 0), navController = rememberNavController())
}
