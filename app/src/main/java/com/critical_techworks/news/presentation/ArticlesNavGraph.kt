package com.critical_techworks.news.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.critical_techworks.article_list.ArticleListScreen
import com.critical_techworks.article_list.ListViewModel
import com.critical_techworks.login.presentation.ui.login.LoginScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

object MainDestinations {
    const val LOGIN_ROUTE = "login"
    const val LIST_ROUTE = "list"
    const val ARTICLE_ROUTE = "article"
}

const val ARTICLE_CONTENT = "ARTICLE_CONTENT"

@ExperimentalPermissionsApi
@Composable
fun ArticlesNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.LOGIN_ROUTE
) {
    val actions = remember(navController) { MainActions(navController) }
    val listViewModel = hiltViewModel<ListViewModel>()
    val articles = listViewModel.articleList
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.LOGIN_ROUTE) {
            LoginScreen(
                navigateToList = actions.navigateToList
            )
        }
        composable(MainDestinations.LIST_ROUTE) {
            ArticleListScreen(
                articles,
                navigateToArticle = actions.navigateToArticle
            )
        }
        composable(
            route = MainDestinations.ARTICLE_ROUTE
        ) {}
    }
}

class MainActions(navController: NavHostController) {
    val navigateToList: () -> Unit = {
        navController.navigate(MainDestinations.LIST_ROUTE) {
            popUpTo(MainDestinations.LOGIN_ROUTE) {
                inclusive = true
            }
        }
    }
    val navigateToArticle: () -> Unit = {
        navController.navigate(MainDestinations.ARTICLE_ROUTE)
    }
}
