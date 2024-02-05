package com.example.effective_testapp.presentation.auth

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.effective_testapp.data.bd.UserData
import com.example.effective_testapp.navigation.NavScreen
import com.example.effective_testapp.navigation.bottomNav.BottomNavScreen
import com.example.effective_testapp.navigation.root.Graph
import com.example.effective_testapp.ui.theme.Effective_testAppTheme
import com.example.effective_testapp.ui.theme.authButtonLock
import com.example.effective_testapp.ui.theme.authButtonUnlock
import com.example.effective_testapp.ui.theme.conditionsText
import com.example.effective_testapp.ui.theme.setColor
import com.example.effective_testapp.ui.theme.textFieldBackGround

@Composable
fun AuthScreen(viewModel: AuthViewModel, navController: NavController) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }

    var isErrorFirstName by remember { mutableStateOf(false) }
    var isErrorLastName by remember { mutableStateOf(false) }
    var isErrorNumber by remember { mutableStateOf(false) }

    var isError by remember { mutableStateOf(false) }
    var isEmpty by remember { mutableStateOf(false) }


    fun validateFields() {
        isErrorFirstName = firstName.isNotEmpty() && !viewModel.inputNameCheck(firstName)
        isErrorLastName = lastName.isNotEmpty() && !viewModel.inputNameCheck(lastName)
        isErrorNumber = number.isNotEmpty() && !viewModel.inputNumberCheck(number)

        isError = isErrorFirstName || isErrorLastName || isErrorNumber
        isEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() && number.isNotEmpty()
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        NameScreen()
        firstName = firstNameField(viewModel)
        lastName = lastNameField(viewModel)
        number = numberField(viewModel)

        validateFields()
        AuthButton(navController, !isError && isEmpty, viewModel, LocalContext.current)
        ConditionsText()

    }
    AuthState.SuccessUser(UserData(firstName = firstName, lastName =  lastName, number =  number))
}

@Composable
fun firstNameField(viewModel: AuthViewModel): String {
    var input by rememberSaveable { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 113.dp),
        value = input,
        onValueChange = { newText ->
            input = newText.trimStart { it == '0' }
            isError = input.isNotEmpty() && !viewModel.inputNameCheck(input)
            if (isError) {
                InputState.ErrorFirst("Error")
            }
        },
        colors = setColor(),
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        placeholder = { Text(text = "Имя") },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        trailingIcon = {
            if (input.isNotEmpty()) {
                IconButton(onClick = { input = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        isError = isError
    )
    InputState.SuccessFirst(input)
    viewModel.getFirstName(input)
    return input
}

@Composable
fun lastNameField(viewModel: AuthViewModel): String {
    var input by rememberSaveable { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(textFieldBackGround),
        maxLines = 1,
        value = input,
        colors = setColor(),
        shape = RoundedCornerShape(8.dp),
        onValueChange = { newText ->
            input = newText.trimStart { it == '0' }
            isError = input.isNotEmpty() && !viewModel.inputNameCheck(input)
            if (isError) {
                InputState.ErrorFirst("Error")
            }
        },
        placeholder = { Text(text = "Фамилия") },
        trailingIcon = {
            if (input.isNotEmpty())
                IconButton(onClick = { input = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        ),
        isError = input.isNotEmpty() && !viewModel.inputNameCheck(input)
    )
    InputState.SuccessFirst(input)
    viewModel.getLastName(input)
    return input
}

@Composable
fun numberField(viewModel: AuthViewModel): String {
    var input by rememberSaveable { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    val mask = "000 000 00 00"
    val maskNumber = '0'
    var isHintVisible by remember { mutableStateOf(true) }

    var isTextFieldEnabled by remember { mutableStateOf(false) }
    var leadingIconVisible by remember { mutableStateOf(false) }

    DisposableEffect(isTextFieldEnabled) {
        leadingIconVisible = isTextFieldEnabled

        onDispose {}
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(textFieldBackGround)
            .onFocusChanged {
                isTextFieldEnabled = it.isFocused
            },
        maxLines = 1,
        value = input,
        colors = setColor(),
        shape = RoundedCornerShape(8.dp),
        onValueChange = { newText ->
            if (newText.isNotEmpty() && newText.first() == '7') {
                input = newText.replace(newText.first().toString(), "")
            } else {
                input = newText
                isError = input.isNotEmpty() && !viewModel.inputNameCheck(input)
                if (isError) {
                    InputState.ErrorFirst("Error")
                }
            }
        },
        visualTransformation =
        if (isHintVisible) {
            PhoneHintVisualTransformation
            PhoneVisualTransformation(mask, maskNumber)
        } else {
            VisualTransformation.None
            PhoneVisualTransformation(mask, maskNumber)
        },
        placeholder = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                Text(
                    text = "Номер телефона",
                    textAlign = TextAlign.Left,
                )
            }

        },
        leadingIcon = {
            if (isTextFieldEnabled) {
                Text("+7", color = Color.Black)
            }
            else{
                leadingIconVisible = false
            }
        },
        trailingIcon = {
            if (input.isNotEmpty())
                IconButton(onClick = { input = "" }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        ),
        isError = input.isNotEmpty() && !viewModel.inputNumberCheck(input)

    )
    InputState.SuccessFirst(input)
    viewModel.getNumber(input)
    return input
}

@Composable
fun AuthButton(navController: NavController, enable: Boolean = false, viewModel: AuthViewModel, context: Context) {
    Button(
        onClick = {
            navController.navigate(Graph.HOME)
            viewModel.insertToDB(context)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(51.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = authButtonUnlock,
            disabledContainerColor = authButtonLock,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = enable
    ) {
        Text(text = "Войти")
    }
}

@Composable
fun ConditionsText() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "Нажимая на кнопку \"Войти\", Вы принимаете",
            color = conditionsText,
            modifier = Modifier
                .padding(bottom = 12.dp),
            fontSize = 10.sp,
            )
        Text(
            text = "условия программы лояльности",
            color = conditionsText,
            fontSize = 10.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun NameScreen() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),

        ) {
        Text(
            text = "Вход",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Effective_testAppTheme {
        AuthScreen(viewModel = AuthViewModel(), navController = rememberNavController())
    }
}