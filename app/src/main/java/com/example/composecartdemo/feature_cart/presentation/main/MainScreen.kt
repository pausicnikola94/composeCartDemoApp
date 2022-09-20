package com.example.composecartdemo.feature_cart.presentation.main

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composecartdemo.feature_cart.presentation.common.ProductItemImage
import com.example.viewdemoapp.feature_cart.presentation.main.MainUiState
import com.example.viewdemoapp.feature_cart.presentation.models.CategorySection

@Composable
fun MainScreen(navController: NavController,
               mainViewModel: MainViewModel) {
    val mainResult = mainViewModel.mainUiState.collectAsState(MainUiState(isLoading = true))

    mainResult.value.isLoading?.let {
        if(!it) return@let
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
    mainResult.value.categorySectionList?.let {
        CategorySections(mainResult, navController)
    }
    mainResult.value.hasError?.let {
        Toast.makeText(LocalContext.current, mainResult.value.hasError, Toast.LENGTH_LONG).show()
    }
}


@Composable
private fun CategorySections(mainResult: State<MainUiState>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().testTag("MainColumn"),
    ) {
        if(mainResult.value.categorySectionList == null) return@LazyColumn
        for(cat in mainResult.value.categorySectionList!!){
            when(cat){
                is CategorySection.CategorySectionElectronics -> {
                    item {
                        Column(modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()) {
                            LazyRow {
                                items(cat.products ?: listOf()) { item ->
                                    ProductItemImage(
                                        width = 170,
                                        height = 150,
                                        padding = 5,
                                        productDetails = item.product,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                is CategorySection.CategorySectionClothing -> {
                    item {
                        Column(modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()) {
                            LazyRow {
                                items(cat.products ?: listOf()) { item ->
                                    ProductItemImage(
                                        width = 150,
                                        height = 200,
                                        padding = 5,
                                        productDetails = item.product,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                is CategorySection.CategorySectionJewelery -> {
                    item {
                        Column(modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()) {
                            LazyRow {
                                items(cat.products ?: listOf()) { item ->
                                    ProductItemImage(
                                        width = 100,
                                        height = 100,
                                        padding = 5,
                                        productDetails = item.product,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
                is CategorySection.CategorySectionName -> {
                    item {
                        CategoryItem(text = cat.id, navController)
                    }
                }
            }
        }
    }
}
