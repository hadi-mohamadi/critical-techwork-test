package com.critical_techworks.article_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.critical_techworks.article_domain.model.Article

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article?,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 16.dp, bottom = 8.dp, start = 16.dp)
            .clickable {
                onCardClick()
            },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(all = 16.dp)) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.primary, CircleShape),
                painter = rememberImagePainter(data = article?.urlToImage,
                    builder = {
                        placeholder(com.critical_techworks.article_util.R.drawable.ic_news)
                        error(com.critical_techworks.article_util.R.drawable.ic_news)

                    }),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier
                    .padding(vertical = 22.dp)
                    .fillMaxWidth(),
                text = article?.title ?: "",
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun ArticleCardPreview() {
    ArticleCard(article = Article(
        source = null,
        author = null,
        title = "This is an example article",
        description = null,
        url = null,
        urlToImage = null,
        publishedAt = null,
        content = null
    ), onCardClick = {})
}
