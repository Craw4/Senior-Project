package edu.hanover.hc24_luuk_crawford_senior_project.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.addOrderCustomization
import edu.hanover.hc24_luuk_crawford_senior_project.data.currentOrder.CurrentOrderManager.removeOrderCustomization

/**
 * Creates a check button that updates order. And labels checkbox.
 * @param selectionName name of customization topping/side
 * @param customizationCategory category of item type (topping/side)
 */
@Composable
fun CheckButtonFor(selectionName: String, customizationCategory: String) {
    var checked by remember { mutableStateOf(false) }
    Row(
        Modifier
            .toggleable(
                value = checked,
                role = Role.Checkbox,
                onValueChange = {
                    checked = !checked
                },
            )
            .padding(10.dp)
            .width(240.dp)
            .background(color = Color(32)),
    ) {
        Checkbox(
            checked = checked,
            { newValue ->
                checked = newValue
            },
            modifier = Modifier.testTag(selectionName),
        )
        if (checked) {
            addOrderCustomization(selectionName, customizationCategory)
        } else {
            removeOrderCustomization(selectionName, customizationCategory)
        }
        Text(
            selectionName,
            Modifier.weight(1f),
            style = TextStyle(fontSize = 25.sp),
            fontWeight = FontWeight.Normal,
        )
    }
}
