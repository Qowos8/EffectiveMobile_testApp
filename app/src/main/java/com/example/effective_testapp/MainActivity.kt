package com.example.effective_testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.effective_testapp.navigation.root.RootNavigationGraph
import com.example.effective_testapp.presentation.auth.AuthViewModel
import com.example.effective_testapp.presentation.catalog.CatalogViewModel
import com.example.effective_testapp.presentation.profile.ProfileViewModel
import com.example.effective_testapp.ui.theme.Effective_testAppTheme

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private val catalogViewModel: CatalogViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Effective_testAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val authNavController = rememberNavController()
                    val catalogNavController = rememberNavController()

                    RootNavigationGraph(navController = rememberNavController(), authViewModel, profileViewModel)

                    //AuthScreen(authViewModel, authNavController)
                    //Navigation(AuthViewModel(), authNavController, catalogNavController)

                   // BottomNavPanel(navController, viewModel = profileViewModel)

                }
            }
        }
    }
}
