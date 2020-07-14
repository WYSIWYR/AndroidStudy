package com.example.project01

import java.io.Serializable

class Student(
    var id: Int? = null,
    var name: String? = null,
    var age: Int? = null,
    var intro: String?= null
): Serializable