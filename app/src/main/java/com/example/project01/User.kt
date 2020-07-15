package com.example.project01

import java.io.Serializable

class User(
    var userId: String? = null,
    var token: String? = null
) : Serializable