package com.example.composecartdemo.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composecartdemo.ui.theme.ComposeCartDemoTheme
import androidx.navigation.compose.rememberNavController
import com.example.composecartdemo.feature_cart.presentation.category_details.CategoryDetailsScreen
import com.example.composecartdemo.feature_cart.presentation.main.MainScreen
import com.example.composecartdemo.feature_cart.presentation.navigation.Screen
import com.example.composecartdemo.feature_cart.presentation.product_details.ProductDetailsScreen
import com.example.composecartdemo.feature_cart.presentation.shopping_cart.ShoppingCartScreen
import com.example.composecartdemo.feature_cart.presentation.main.MainViewModel
import com.example.viewdemoapp.feature_cart.presentation.category_details.CategoryDetailsViewModel
import com.example.viewdemoapp.feature_cart.presentation.models.ProductDetails
import com.example.viewdemoapp.feature_cart.presentation.product_details.ProductDetailsViewModel
import com.example.viewdemoapp.feature_cart.presentation.shopping_cart.ShoppingCartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val mainViewModel = hiltViewModel<MainViewModel>()
            ComposeCartDemoTheme {
                Scaffold(
                    topBar = {
                        com.example.composecartdemo.feature_cart.presentation.common.TopAppBar(navController = navController,
                            mainViewModel)
                    }
                ){
                    NavHost(navController, Screen.MainScreen.route) {
                        composable(Screen.MainScreen.route) {
                            MainScreen(navController, mainViewModel)
                        }
                        composable(Screen.ProductDetailsScreen.route) {
                            val viewModel = hiltViewModel<ProductDetailsViewModel>()
                            val result = navController.previousBackStackEntry?.savedStateHandle?.get<ProductDetails>("product")
                            ProductDetailsScreen(viewModel, result)
                        }
                        composable(Screen.ShoppingCartScreen.route) {
                            val viewModel = hiltViewModel<ShoppingCartViewModel>()
                            ShoppingCartScreen(navController, viewModel)
                        }
                        composable(Screen.CategoryDetailsScreen.route + "/{categoryId}") {
                            val viewModel = hiltViewModel<CategoryDetailsViewModel>()
                            CategoryDetailsScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}

