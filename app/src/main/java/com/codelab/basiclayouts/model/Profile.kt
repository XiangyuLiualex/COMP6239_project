package com.codelab.basiclayouts.model

import com.codelab.basiclayouts.ui.components.Sex
import com.codelab.basiclayouts.ui.screens.shared.Identity
import kotlin.random.Random

data class Profile(
    val username: String = "",
    val realName: String = "",
    val selfDescription: String = "",
    val email: String = "",
    val telephone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val profilePictureLink: String = ""
)

object SelectedIdentity {
    var selectedIdentity = Identity.READER
}
object SelectedSex {
    var selectedSex = Sex.MALE
}

object LoginUser {
    var email: String = ""
    var password: String = ""
}

val allProfile: List<Profile> = generateRandomProfileList()

fun generateRandomProfileList(): List<Profile> {
    val usernames = listOf("user1", "user2", "user3", "user4", "user5")
    val realNames = listOf("Alice Smith", "Bob Johnson", "Charlie Brown", "David Lee", "Emma Davis")
    val selfDescriptions = listOf("I love coding!", "Exploring the world!", "Foodie forever!", "Music is life!", "Sports enthusiast!")
    val emails = listOf("user1@example.com", "user2@example.com", "user3@example.com", "user4@example.com", "user5@example.com")
    val telephones = listOf("1234567890", "9876543210", "5551234567", "8889990000", "7778889999")
    val passwords = listOf("password1", "password2", "password3", "password4", "password5")
    val profilePictureLinks = listOf(
        "https://example.com/profile1.jpg",
        "https://example.com/profile2.jpg",
        "https://example.com/profile3.jpg",
        "https://example.com/profile4.jpg",
        "https://example.com/profile5.jpg"
    )

    val random = Random(System.currentTimeMillis())

    return List(5) {
        val username = usernames[random.nextInt(usernames.size)]
        val realName = realNames[random.nextInt(realNames.size)]
        val selfDescription = selfDescriptions[random.nextInt(selfDescriptions.size)]
        val email = emails[random.nextInt(emails.size)]
        val telephone = telephones[random.nextInt(telephones.size)]
        val password = passwords[random.nextInt(passwords.size)]
        val confirmPassword = password // Assuming confirmPassword is the same as password
        val profilePictureLink = profilePictureLinks[random.nextInt(profilePictureLinks.size)]

        Profile(username, realName, selfDescription, email, telephone, password, confirmPassword, profilePictureLink)
    }
}