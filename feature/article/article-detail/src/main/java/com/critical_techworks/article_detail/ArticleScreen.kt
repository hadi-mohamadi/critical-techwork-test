package com.critical_techworks.article_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.critical_techworks.article_util.temp.TempObject
import com.critical_techworks.article_util.theme.Content
import com.critical_techworks.article_util.theme.Description


@Composable
fun ArticleScreen() {
    val article = TempObject.article
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
            .verticalScroll(state = scrollState)
    ) {
        article.urlToImage?.let {
            Spacer(modifier = Modifier.size(16.dp))
            ArticleImage(urlToImage = it)
        }
        article.title?.let {
            Spacer(modifier = Modifier.size(16.dp))
            ArticleTitle(title = it)
        }
        article.description?.let {
            Spacer(modifier = Modifier.size(16.dp))
            ArticleDescription(description = it)
        }
        article.content?.let {
            Spacer(modifier = Modifier.size(16.dp))
            ArticleContent(content = it)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ArticleImage(modifier: Modifier = Modifier, urlToImage: String) {
    Image(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(1.5.dp, MaterialTheme.colors.primary),
        painter = rememberImagePainter(data = urlToImage,
            builder = {
                placeholder(com.critical_techworks.article_util.R.drawable.ic_news)
                error(com.critical_techworks.article_util.R.drawable.ic_news)
            }),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ArticleTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = title,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Justify,
        fontSize = 20.sp
    )
}

@Preview
@Composable
fun ArticleTitlePreview() {
    ArticleContent(content = "Stocks rise, U.S. yields slip as markets await Fed rate hike - Reuters")
}

@Composable
fun ArticleDescription(modifier: Modifier = Modifier, description: String) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = Description + description,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Justify,
        fontSize = 18.sp
    )
}


@Preview
@Composable
fun ArticleDescriptionPreview() {
    ArticleDescription(description = "A gauge of global equity markets edged higher on Tuesday while 10-year U.S. Treasury yields slid from the 3% level as investors remained cautious, expecting the Federal Reserve to hike rates by the most in a single day since 2000 to curb inflation.")
}

@Composable
fun ArticleContent(modifier: Modifier = Modifier, content: String) {
    Text(
        modifier = modifier.padding(horizontal = 16.dp),
        text = Content + content,
        color = MaterialTheme.colors.onSurface,
        textAlign = TextAlign.Justify,
        fontSize = 18.sp
    )
}

@Preview
@Composable
fun ArticleContentPreview() {
    ArticleContent(content = "NEW YORK/MILAN, May 3 (Reuters) - A gauge of global equity markets edged higher on Tuesday while 10-year U.S. Treasury yields slid from the 3% level as investors remained cautious, expecting the Fede… [+4444 chars] ")
}
