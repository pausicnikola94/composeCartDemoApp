package com.example.composecartdemo.presentation.end_to_end

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composecartdemo.core.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailsEndToEndTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun productDetails_addedAndRemovedFromCart(){
        // Wait for data to be loaded
        composeTestRule.waitUntil(timeoutMillis = 60000) {
            composeTestRule
                .onAllNodesWithText("Electronics")
                .fetchSemanticsNodes().size == 1

        }

        // Click on first item inside second main recycler view item - go to Product Details
        composeTestRule.onNodeWithTag("MainColumn").onChildren().get(1).onChildren().get(0).performClick()

        // Add product to cart
        composeTestRule.onNodeWithTag("CartCard")
            .onChildren().get(2).performClick()

        // Check is amount updated
        composeTestRule.onNodeWithTag("CartCard")
            .onChildren().get(1).assertTextContains("1")

        // Remove product from cart
        composeTestRule.onNodeWithTag("CartCard")
            .onChildren().get(0).performClick()

        // Check is amount updated
        composeTestRule.onNodeWithTag("CartCard")
            .onChildren().get(1).assertTextContains("0")
    }

}