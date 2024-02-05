package com.example.effective_testapp.presentation.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.effective_testapp.R
import com.example.effective_testapp.data.network.entity.Product
import com.example.effective_testapp.ui.theme.Effective_testAppTheme
import com.example.effective_testapp.ui.theme.setButtonColor
import com.example.effective_testapp.ui.theme.setButtonColorCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CatalogScreen(navController: NavHostController) {
    val filterList: List<String> =
        listOf("По популярности", "По уменьшению цены", "По возрастанию цены")
    val storeItemsImages: List<Painter> = listOf(
        (painterResource(id = R.drawable.body_los)),
        painterResource(id = R.drawable.face_mask),
        painterResource(id = R.drawable.face_salf),
        painterResource(id = R.drawable.hand_mask),
        painterResource(id = R.drawable.penka),
        painterResource(id = R.drawable.razor)
    )
    val viewModel = CatalogViewModel()
    viewModel.loadData()
    /*Scaffold (
        bottomBar = { com.example.effective_testapp.navigation.root.BottomNavPanel(navController = navController) }
    ){
        HomeNavGraph(navController = navController, padding = it)*/
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        NameScreen()
        FilterButtons()
        ScrollFilters()
        LoadData(viewModel, storeItemsImages)
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
            text = "Каталог",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun FilterButtons() {
    var enable = false
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.Start

    ) {
        Button(
            onClick = { enable = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(
                text = "По популярности",
                fontSize = 16.sp,
            )
            Icon(
                imageVector = if (!enable) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.Default.KeyboardArrowDown
                },
                contentDescription = null
            )

        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContainerColor = Color.White,
                disabledContentColor = Color.Black
            )
        )
        {
            Image(
                painter = painterResource(id = R.drawable.filter2),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp),
            )
            Text(
                text = "Фильтры",
                fontSize = 16.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun ScrollFilters() {
    val buttonList: List<String> = listOf("Смотреть все", "Лицо", "Тело", "Загар", "Маски")
    var selectedButtonIndex by remember { mutableStateOf(0) }

    //var selectedFilter by remember { mutableStateOf("") }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(buttonList) { buttonName ->
            FilterButton(buttonName)
        }
    }
}

@Composable
fun FilterButton(name: String) {
    var enable by remember { mutableStateOf(true) }
    Button(
        onClick = {
            enable = !enable
        },
        colors = if (enable) {
            setButtonColor()
        } else {
            setButtonColorCheck()
        },
    ) {
        Text(
            text = name
        )
    }
}
@Composable
fun LoadData(viewModel: CatalogViewModel, images: List<Painter>){
    val userData by viewModel.responseState.observeAsState()
    when(userData){
        is ResponseState.Success ->{
            StoreList(cardItems = userData as ResponseState.Success, images)
        }

        else -> {}
    }
}
@Composable
fun StoreList(cardItems: ResponseState.Success, images: List<Painter>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(cardItems.response.size) { cardItem ->
            StoreCard(cardItems.response[cardItem], images[cardItem])
        }
    }
}

@Composable
fun StoreCard(data: Product, image: Painter) {
    var checked by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        //elevation = 8.dp
    ) {
        Column {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                IconToggleButton(
                    checked = checked,
                    onCheckedChange = { _checked ->
                        checked = _checked
                    },
                    modifier = Modifier.size(24.dp),
                    interactionSource = NoRippleInteractionSource()
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite Item",
                        tint = if (checked) Color.Magenta else Color.Gray
                    )
                }
            }
        }
    }
}

data class CardItem(val title: String, val painter: Painter)


class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Effective_testAppTheme {
        CatalogScreen(navController = rememberNavController())
    }
}