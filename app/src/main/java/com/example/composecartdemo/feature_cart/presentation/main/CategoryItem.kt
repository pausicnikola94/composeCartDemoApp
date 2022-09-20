package com.example.composecartdemo.feature_cart.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composecartdemo.feature_cart.presentation.common.navigateCustom
import com.example.composecartdemo.feature_cart.presentation.navigation.Screen


@Composable
fun CategoryItem(text: String?, navController: NavController){
    Surface {
        text?.let {
            Row (
                Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = text.replaceFirstChar { it.uppercase() },
                    modifier = Modifier
                        .clickable { }
                        .weight(1f)
                        .clickable {
                            navController.navigateCustom(
                                Screen.CategoryDetailsScreen,
                                it
                            )
                        },
                    fontSize = 25.sp
                )
                IconButton(onClick = { navController.navigateCustom(Screen.CategoryDetailsScreen, it)  }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "Enter")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemPreview(){
    CategoryItem(text = "electronics", navController = rememberNavController() )
}