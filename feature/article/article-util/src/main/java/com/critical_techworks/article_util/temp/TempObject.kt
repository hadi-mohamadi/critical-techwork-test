package com.critical_techworks.article_util.temp

import com.critical_techworks.article_domain.model.Article

class TempObject {
    companion object {
        /**
         * We use this object because there is a limit for the Json-String that you can pass via NavHostController.
         */
        lateinit var article: Article
    }
}