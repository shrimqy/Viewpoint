package com.dokja.viewpoint.utils

class UsernameAlreadyExistsException(message: String) : RuntimeException(message)
class InvalidCredentialsException(message: String) : RuntimeException(message)