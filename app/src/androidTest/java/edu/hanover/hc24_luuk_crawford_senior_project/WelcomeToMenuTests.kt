package edu.hanover.hc24_luuk_crawford_senior_project

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import edu.hanover.hc24_luuk_crawford_senior_project.composables.AppNavHost
import edu.hanover.hc24_luuk_crawford_senior_project.composables.screens.WelcomeScreen
import edu.hanover.hc24_luuk_crawford_senior_project.data.Destination
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuItem
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class WelcomeToMenuTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Clears any changes then adds default MenuItem for each test.
     */
    @Before
    fun onlySomethingInList() {
        MenuData.clearMenuContents()
        MenuData.addMenuItem(MenuItem())
    }

    /**
     * Folder was originally called zybooks.
     */
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.zybooks.hc24_luuk_crawford_senior_project", appContext.packageName)
    }

    /**
     * Opens welcome screen, ensures buttons and such exist.
     */
    @Test
    fun welcomeScreenExistsTest() {
        composeTestRule.setContent {
            WelcomeScreen {
            }
        }
        checkWelcomeScreenDisplayed()
    }

    /**
     * Tests navigation from Welcome screen to Menu.
     * Performs text input to allow navigation.
     */
    @Test
    fun navigateFromWelcomeToMenuTest() {
        composeTestRule.setContent {
            AppNavHost(Destination.welcomeScreen.name)
        }
        val beginOrderButton = composeTestRule.onNode(hasTestTag("beginOrderButton"), true)
        val inputUserNameArea = composeTestRule.onNode(hasTestTag("inputUserName"))

        inputUserNameArea.assertExists()
        inputUserNameArea.performTextInput("My Name")
        beginOrderButton.performClick()

        checkMenuScreenContents()
        beginOrderButton.assertDoesNotExist()
    }

    private fun checkWelcomeScreenDisplayed() {
        val beginOrderButton = composeTestRule.onNode(hasTestTag("beginOrderButton"), true)
        beginOrderButton.assertIsDisplayed()

        val locationInfoLogoText = composeTestRule.onNode(hasTestTag("collegeNameText"), true)
        locationInfoLogoText.assertIsDisplayed()

        val undergroundText = composeTestRule.onNode(hasTestTag("undergroundText"))
        undergroundText.assertIsDisplayed()

        val inputUserNameArea = composeTestRule.onNode(hasTestTag("inputUserName"))
        inputUserNameArea.assertIsDisplayed()
    }

    /**
     * Opens menuScreen and checks to ensure everything exists
     */
    @Test
    fun menuScreenExistsTest() {
        composeTestRule.setContent {
            AppNavHost(Destination.menuScreen.name)
        }
        checkMenuScreenContents()
    }

    /**
     * Checks to ensure basic menuScreen components are currently present.
     */
    private fun checkMenuScreenContents() {
        val hcLogoText = composeTestRule.onNode(hasTestTag("collegeNameText"))
        hcLogoText.assertIsDisplayed()
        val otherPageUndergroundText = composeTestRule.onNode(hasTestTag("undergroundText"))
        otherPageUndergroundText.assertDoesNotExist()
        val itemExistsInList = composeTestRule.onNode(hasTestTag("default item"))
        itemExistsInList.assertIsDisplayed()
    }

    /**
     * Ensures it does not switch pages without user's name.
     * (beginOrderButton would go away if page changed).
     */
    @Test
    fun noNamePreventNavigateFromWelcomeToMenu() {
        composeTestRule.setContent {
            AppNavHost(Destination.welcomeScreen.name)
        }
        checkWelcomeScreenDisplayed()

        val beginOrderButton = composeTestRule.onNode(hasTestTag("beginOrderButton"), true)
        val inputUserNameArea = composeTestRule.onNode(hasTestTag("inputUserName"))

        beginOrderButton.performClick()
        inputUserNameArea.performTextInput("")
        beginOrderButton.performClick()
        checkWelcomeScreenDisplayed()
    }
}
