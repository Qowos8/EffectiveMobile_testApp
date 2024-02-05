package com.example.effective_testapp.presentation.profile

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.effective_testapp.R
import com.example.effective_testapp.ui.theme.Effective_testAppTheme
import com.example.effective_testapp.ui.theme.textFieldBackGround

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel = ProfileViewModel()
    val names: List<String> = listOf("Избранное", "Магазины", "Обратная связь", "Оферта", "Возврат товара")
    val painters: List<Painter> = listOf(
        painterResource(id = R.drawable.heart),
        painterResource(id = R.drawable.storee),
        painterResource(id = R.drawable.message),
        painterResource(id = R.drawable.offer),
        painterResource(id = R.drawable.back),

        )
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        NameScreen()
        UserFields(viewModel, LocalContext.current)
        Spacer(modifier = Modifier.height(8.dp))
        BuildList(names = names, painters = painters)
    }
}

@Composable
fun ProfileButton(dataState: DataState) {
    val datas = (dataState as DataState.SuccessUser).user
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = textFieldBackGround
            )
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .weight(0.7f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${datas.firstName} ${datas.lastName}",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = "+7 ${datas.number}",
                    color = Color.Black,
                    fontSize = 10.sp
                )
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Composable
fun NotFunctionalButtons(name: String, painter: Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
                .height(49.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = textFieldBackGround
            )
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp),
                painter = painter,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = name,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.weight(0.7f),
                //textAlign = TextAlign.Center
            )

            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}


@Composable
fun UserFields(viewModel: ProfileViewModel, context: Context) {
    val userData by viewModel.bdState.observeAsState()
    viewModel.getUserData(context)
    when (userData) {
        is DataState.SuccessUser -> {
            ProfileButton(userData as DataState.SuccessUser)
            Log.d("userData", (userData as DataState.SuccessUser).user.number)
        }

        else -> {}
    }
}

@Composable
fun NameScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Личный кабинет",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }

}
@Composable
fun BuildList(names: List<String>, painters: List<Painter>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(names.size) { index ->
            NotFunctionalButtons(name = names[index], painter = painters[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Effective_testAppTheme {
        ProfileScreen(navController = rememberNavController())
    }
}