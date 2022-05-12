pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "news"
include(":app")
include(":core:core-ui")
include(":core:core-network")
include(":feature:login")
include(":feature:article:article-domain")
include(":feature:article:article-repository")
include(":feature:article:article-di")
