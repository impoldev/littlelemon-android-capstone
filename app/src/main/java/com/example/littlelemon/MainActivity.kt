package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MenuDatabase::class.java,
            "menu.db"
        )
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                Navigation(database.menuDao())
            }
        }

        lifecycleScope.launch {
            withContext(IO) {
                if (database.menuDao().isEmpty()) {
                    val menu: List<MenuItemNetwork> = getMenu()
                    saveMenuToDatabase(menu)
                }
            }
        }
    }

    private suspend fun getMenu(): List<MenuItemNetwork> {
        return try {
            val response: MenuNetworkData = client
                .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
                .body()
            response.menu
        } catch (e: Exception) {
            Log.e("DEBUG", "Network error", e)
            emptyList()
        }
    }

    private suspend fun saveMenuToDatabase(menuItems: List<MenuItemNetwork>) {
        for (item in menuItems) {
            val newItem = MenuItem(
                id = item.id,
                title = item.title,
                description = item.description,
                price = item.price,
                image = item.image,
                category = item.category
            )
            database.menuDao().saveMenuItem(newItem)
        }
    }
}