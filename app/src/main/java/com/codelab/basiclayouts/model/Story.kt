package com.codelab.basiclayouts.model

import com.codelab.basiclayouts.R

data class Story(
    val title: String,
    val author: String,
    val imageUrl: String,
    val description: String,
    val progress: String,
    val currentChapter: String,
    val category: String
)

val allStories: List<Story> = listOf(
    Story(
        "Find Dad",
        "Jack",
        R.drawable.share_sweet_franky.toString(),
        "Tadpole Searching for Its Father",
        "30%",
        "Going to a new city",
        "Funny"
    ),
    Story(
        "Race",
        "Janney",
        R.drawable.share_sweet_franky.toString(),
        "Turtle and Belly Race",
        "10%",
        "Rabbits sleeping",
        "Animal"
    ),
    Story(
        "Radiant",
        "Monney",
        R.drawable.share_sweet_franky.toString(),
        "The daily life of a group of underage children",
        "15%",
        "Searching for the Son of the Sheep",
        "Animal"
    ),
    Story(
        "Princess and Prince",
        "Dany",
        R.drawable.share_sweet_franky.toString(),
        "The Love Life of a Beautiful Princess and a Fallen Prince",
        "30%",
        "The Prince's Past",
        "Love"
    ),
    Story(
        "Train Man",
        "Magic",
        R.drawable.share_sweet_franky.toString(),
        "A group of locomotives with superpowers that can transform",
        "50%",
        "Train upgrade",
        "Science-Fiction"
    ),
)

val favoStories = allStories.subList(2, 5)
val librStories = allStories.subList(3, 5)