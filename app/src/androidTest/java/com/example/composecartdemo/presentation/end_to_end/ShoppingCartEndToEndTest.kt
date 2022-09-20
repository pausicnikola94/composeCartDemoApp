package com.example.composecartdemo.presentation.end_to_end

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.composecartdemo.core.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingCartEndToEndTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shoppingCart_addedAndRemovedFromCart(){
        // Wait for data to be loaded
        composeTestRule.waitUntil(timeoutMillis = 60000) {
            composeTestRule
                .onAllNodesWithText("Electronics")
                .fetchSemanticsNodes().size == 1

        }

        // Click on first category name  - go to Category Details Screen
        composeTestRule.onNodeWithText("Electronics").performClick()

        // Add first product to cart
        composeTestRule.onNodeWithTag("CategoryDetailsColumn")
            .onChildren().get(0).onChildren().get(1).onChildren().get(2).performClick()

        // Go back to main
        Espresso.pressBack()

        // Go to shopping cart
        composeTestRule.onNodeWithTag("CartIcon").performClick()

        // Check is amount updated
        composeTestRule.onNodeWithTag("ShoppingCartColumn")
            .onChildren().get(0).onChildren().get(1).onChildren().get(1).assertTextContains("1")

        // Remove first product from cart
        composeTestRule.onNodeWithTag("ShoppingCartColumn")
            .onChildren().get(0).onChildren().get(1).onChildren().get(0).performClick()

        // Check is amount updated (popback stack should happen)
        composeTestRule.onNodeWithTag("MainColumn").assertIsDisplayed()
    }
}