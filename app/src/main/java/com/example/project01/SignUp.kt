package com.example.project01

import java.io.Serializable

class SignUp (
    var userId: String? = null,
    var userPwd: String? = null,
    var userPwdConfirm: String? = null
): Serializable