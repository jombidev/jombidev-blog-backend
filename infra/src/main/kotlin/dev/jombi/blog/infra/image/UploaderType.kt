package dev.jombi.blog.infra.image

sealed interface UploaderType { // https://youtrack.jetbrains.com/issue/KT-16304 Enum#name is not constant. so working with sealed thingy
    data object Local : UploaderType {
        const val NAME = "local"
    }
    data object Oracle : UploaderType {
        const val NAME = "oracle"
    }
    companion object {
        fun guess(name: String): UploaderType = when (name) {
            Local.NAME -> Local
            Oracle.NAME -> Oracle
            else -> throw IllegalArgumentException(name)
        }
    }
}
