package com.critical_techworks.article_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_interactors.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getNews: GetNews
) :
    ViewModel() {
    lateinit var articleList: Flow<PagingData<Article>>

    init {
        viewModelScope.launch {
            articleList = getNews()
        }
    }

}