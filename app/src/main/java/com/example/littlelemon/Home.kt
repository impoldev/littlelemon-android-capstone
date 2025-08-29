package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController, database: MenuDao) {
    val scrollState = rememberScrollState()
    val menuItems by database.getAllMenuItems().observeAsState(emptyList())
    var searchPhrase: String by remember { mutableStateOf("") }
    var searchCategory: String by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon logo",
                    modifier = Modifier
                        .width(260.dp)
                        .height(80.dp)
                        .padding(horizontal = 20.dp)
                )
                IconButton(
                    onClick = {navController.navigate(Profile.route)},
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile image",
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(percent = 100)
                            )
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xFF485E57))
                        .padding(16.dp)
                ) {
                    Column {
                        Text(
                            text = "Little Lemon",
                            fontSize = 40.sp,
                            color = Color(0xFFF4CE14)
                        )
                        Text(
                            text = "Chicago",
                            fontSize = 22.sp,
                            color = Color.White
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                        ) {
                            Text(
                                text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                                fontSize = 16.sp,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth(0.55f)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.hero_image),
                                contentDescription = "Hero image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(140.dp)
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(color = Color.LightGray)
                        ) {
                            TextField(
                                value = searchPhrase,
                                placeholder = { Text(text = "Enter search phrase") },
                                onValueChange = { it -> searchPhrase = it},
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search icon"
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        "ORDER FOR DELIVERY!",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                searchCategory = "starters"
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(
                                start = 12.dp,
                                top = 5.dp,
                                end = 12.dp,
                                bottom = 5.dp
                            )
                        ) {
                            Text(
                                "Starters",
                            )
                        }

                        Button(
                            onClick = {
                                searchCategory = "mains"
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(
                                start = 12.dp,
                                top = 5.dp,
                                end = 12.dp,
                                bottom = 5.dp
                            )
                        ) {
                            Text(
                                "Mains",
                            )
                        }

                        Button(
                            onClick = {
                                searchCategory = "desserts"
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(
                                start = 12.dp,
                                top = 5.dp,
                                end = 12.dp,
                                bottom = 5.dp
                            )
                        ) {
                            Text(
                                "Desserts",
                            )
                        }

                        Button(
                            onClick = {
                                searchCategory = "drinks"
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Black
                            ),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(
                                start = 12.dp,
                                top = 5.dp,
                                end = 12.dp,
                                bottom = 5.dp
                            )
                        ) {
                            Text(
                                "Drinks",
                            )
                        }
                    }
                }

                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                )

                val filteredItems = if (!searchPhrase.isEmpty()) {
                    menuItems.filter { item ->
                        item.title.contains(searchPhrase, ignoreCase = true)
                    }
                } else {
                    menuItems
                }

                val filteredItemsByCategory = if (!searchCategory.isEmpty()) {
                    filteredItems.filter { item ->
                        item.category.contains(searchCategory, ignoreCase = true)
                    }
                } else {
                    filteredItems
                }

                MenuItems(filteredItemsByCategory)
            }
        }
    }
}

@Composable
fun MenuItems(menuItems: List<MenuItem>) {
    Column {
        menuItems.forEach { item ->
            MenuItem(item.title, item.price, item.description, item.image)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(title: String, price: String, description: String, image: String) {
    Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(
                    text = description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "$$price.00",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
            }

            Column {
                if (LocalInspectionMode.current) {
                    // Only for preview mode
                    Image(
                        painter = painterResource(id = R.drawable.bruschetta),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                    )
                } else {
                    // For emulator
                    GlideImage(
                        model = image,
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                    )
                }
            }
        }
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 15.dp)
        )
    }
}

class FakeMenuDao : MenuDao {
    private val sampleMenu = listOf(
        MenuItem(1, "Pizza", "Delicious pizza lorem asda sdasd asdasd asda asd asd asd asd asd asd asd asd asd asd asd asd asd asd asd", "10", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true", "mains"),
        MenuItem(2, "Burger", "Juicy burger lorem asda sdasd asdasd asda asd asd asd asd asd asd asd asd asd asd asd asd asd asd asd", "8", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true", "mains"),
        MenuItem(3, "Salad", "Fresh salad lorem asda sdasd asdasd a s d a a s d a s d a s d asd asd asd asd asd asd asd asd asd asd asd asd", "6", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/grilledFish.jpg?raw=true", "mains")
    )

    private val liveData = MutableLiveData(sampleMenu)
    override fun getAllMenuItems(): LiveData<List<MenuItem>> = liveData
    override suspend fun saveMenuItem(menuItem: MenuItem) {
        val current = liveData.value.orEmpty().toMutableList()
        current.add(menuItem)
        liveData.value = current
    }
    override suspend fun getCount(): Int = liveData.value?.size ?: 0
    override fun isEmpty(): Boolean = liveData.value.isNullOrEmpty()
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    val fakeDao = FakeMenuDao()
    Home(navController, fakeDao)
}