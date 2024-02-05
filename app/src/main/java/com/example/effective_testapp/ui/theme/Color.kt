package com.example.effective_testapp.ui.theme

import android.widget.ToggleButton
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val textFieldBackGround = Color(0xFFF8F8F8)
val conditionsText = Color(0xFFA0A1A3)

val authButtonLock = Color(0xFFFF8AC9)
val authButtonUnlock = Color(0xFFD62F89)

val itemSelcetedColor = Color(0xFFFF8AC9)

val containerButtonColor = Color(0xFF52606D)

@Composable
fun setColor(): TextFieldColors {
    return TextFieldDefaults.colors(
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Red,
        focusedContainerColor = textFieldBackGround,
        unfocusedContainerColor = textFieldBackGround,
        disabledContainerColor = textFieldBackGround,
        errorContainerColor = textFieldBackGround
    )
}

@Composable
fun setBottomItemColor(): NavigationBarItemColors {
    return colors(
        selectedIconColor = authButtonUnlock,
        selectedTextColor = authButtonUnlock,
        unselectedIconColor = Color.Black,
        unselectedTextColor = Color.Black,
        disabledIconColor = Color.Red,
        disabledTextColor = Color.Red,
        indicatorColor = Color.White
    )
}

@Composable
fun setButtonColor(): ButtonColors {
    return buttonColors(
        containerColor = textFieldBackGround,
        contentColor = conditionsText,
        disabledContainerColor = containerButtonColor,
        disabledContentColor = Color.White
    )
}
@Composable
fun setButtonColorCheck(): ButtonColors {
    return buttonColors(
        containerColor = containerButtonColor,
        contentColor = Color.White,
        disabledContainerColor = containerButtonColor,
        disabledContentColor = Color.White
    )
}

@Composable
fun setToggleButtonColor(): IconToggleButtonColors {
    return IconButtonDefaults.iconToggleButtonColors(
        containerColor = textFieldBackGround,
        contentColor = conditionsText,
        disabledContainerColor = containerButtonColor,
        disabledContentColor = Color.White,
        checkedContainerColor = containerButtonColor,
        checkedContentColor = Color.White
    )
}