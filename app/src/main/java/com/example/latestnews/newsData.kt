package com.example.latestnews

data class newsData(
    val status : String,
    val totalResults: Int,
    val articles : List<Article>
)


data class Article(
    val author : String,
    val title : String,
    val description : String,
    val url : String ,
    val urlToImage : String,
    val publishedAt : String,
    val content : String

)

data class Source(
    val id : Int,
    val name : String
)