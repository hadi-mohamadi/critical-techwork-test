package com.critical_techworks.article_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_interactors.GetNews
import com.critical_techworks.core_network.util.RepositoryResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ListViewModelTest {

    @get:Rule
    val instantTaskRule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var getNews: GetNews

    private lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    private fun createViewModel(): ListViewModel {
        return ListViewModel(
            getNews = getNews
        )
    }

    @Test
    fun when_listViewModel_is_initialized_and_getNews_returns_list_of_articles_articleList_must_be_Updated_with_list_of_articles() {
        runBlocking {
            val expectedData = flow<PagingData<Article>> { PagingData.from(listOf(Article(),
                Article()
            )) }
            coEvery { getNews() } coAnswers { expectedData }
            listViewModel = createViewModel()
            delay(1_000L)
            assert(listViewModel.articleList == expectedData)
        }
    }

    @Test
    fun when_listViewModel_is_initialized_and_getNews_returns_error_articleList_must_be_Updated_with_error() {
        runBlocking {
            val expectedData = flow<PagingData<Article>> { PagingData.from(listOf(Article(),
                Article()
            )) }
            coEvery { getNews() } throws Throwable()
            listViewModel = createViewModel()
            delay(1_000L)
            assert(listViewModel.articleList == expectedData)
        }
    }

}