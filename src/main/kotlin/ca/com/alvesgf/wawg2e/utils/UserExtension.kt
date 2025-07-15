package ca.com.alvesgf.wawg2e.utils

import ca.com.alvesgf.wawg2e.model.User
import ca.com.alvesgf.wawg2e.model.UserInfo

fun UserInfo.createUser(): User = User(this.name, this.email, this.birthDate, this.username)