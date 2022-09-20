package com.example.composecartdemo.feature_cart.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecartdemo.R
import com.example.viewdemoapp.feature_cart.presentation.models.ProductDetails

@Composable
fun CartCard(width: Int?,
             productDetails: ProductDetails?,
             addToCart: (ProductDetails) -> Unit,
             removeFromCart: (ProductDetails) -> Unit) {
    var modifier = Modifier.height(80.dp).padding(15.dp).testTag("CartCard")
    modifier = if(width == null){
        modifier.then(Modifier.fillMaxWidth())
    } else {
        modifier.then(Modifier.width(width.dp))
    }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        if(productDetails == null) return@Card
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart) {
            IconButton(onClick = {
                removeFromCart(productDetails)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_24), contentDescription = "Remove")
            }
        }

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            contentAlignment = Alignment.Center) {
            Text(
                text = productDetails.quantity.toString(),
                modifier = Modifier
                    .clickable { },
                fontSize = 20.sp,
            )
        }

        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd) {
            IconButton(onClick = {
                addToCart(productDetails)
            },
                modifier = Modifier.testTag("add")) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    }
}