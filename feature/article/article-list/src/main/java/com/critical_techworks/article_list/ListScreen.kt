package com.critical_techworks.article_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_util.temp.TempObject
import com.critical_techworks.article_util.theme.Source
import com.critical_techworks.core_ui.theme.LabelRetry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ArticleListScreen(articles: Flow<PagingData<Article>>, navigateToArticle: () -> Unit) {
    val lazyMovieItems = articles.collectAsLazyPagingItems()
    Column {
        Title(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .padding(top = 32.dp),
            text = BuildConfig.source
        )
        LazyColumn(contentPadding = PaddingValues(top = 16.dp)) {
            items(items = lazyMovieItems) { article ->
                ArticleCard(article = article, onCardClick = {
                    TempObject.article = article!!
                    navigateToArticle()
                })
            }

            lazyMovieItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = lazyMovieItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                modifier = Modifier.fillParentMaxSize(),
                                onClickRetry = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = lazyMovieItems.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                message = e.error.localizedMessage!!,
                                onClickRetry = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ArticleListScreenPreview() {
    ArticleListScreen(articles = flowOf()) {}
}


@Composable
fun ErrorContent(modifier: Modifier = Modifier, errorMessage: String?, onRetryClicked: () -> Unit) {
    if (errorMessage.isNullOrEmpty()) return
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error_connection),
            contentDescription = "",
            modifier = Modifier
                .width(64.dp)
                .height(64.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(text = errorMessage, fontSize = 16.sp, color = MaterialTheme.colors.error)
        Spacer(Modifier.height(16.dp))
        Text(
            text = LabelRetry,
            Modifier.clickable {
                onRetryClicked()
            },
            fontSize = 18.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview
@Composable
private fun ErrorContentPreview() {
    ErrorContent(errorMessage = "connection error", onRetryClicked = {})
}

@Composable
private fun Title(modifier: Modifier = Modifier, text: String) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = Source + text,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.size(8.dp))
        Divider(color = MaterialTheme.colors.primary)
    }
}

@Preview
@Composable
private fun TitlePreview() {
    Title(text = "This is the title")
}
