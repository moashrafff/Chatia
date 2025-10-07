package com.chatia.project.domain.validator

sealed class Validator {
    abstract fun validate(): Boolean
    class LengthValidator(val value: String, val min: Int?, val max: Int?) : Validator() {
        override fun validate(): Boolean {
            return value.length in (min ?: 0)..(max ?: Int.MAX_VALUE)
        }
    }

    class EmptyOrNullValidator(val value: String?) : Validator() {
        override fun validate(): Boolean = value.isNullOrBlank()
    }

    class RegexValidator(val value: String, val regex: Regex) : Validator() {
        override fun validate(): Boolean = regex.matches(value)
    }
}
