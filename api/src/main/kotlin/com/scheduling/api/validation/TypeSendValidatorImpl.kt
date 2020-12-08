package com.scheduling.api.validation

import com.scheduling.api.model.SendTypeDTO
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TypeSendValidatorImpl : ConstraintValidator<TypeSendValidator, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return valueOf<SendTypeDTO>(value ?: "")
    }
}

inline fun <reified T : Enum<T>> valueOf(type: String): Boolean {
    try {
        java.lang.Enum.valueOf(T::class.java, type)
        return true
    } catch (e: IllegalArgumentException) {
        return false
    }
}
