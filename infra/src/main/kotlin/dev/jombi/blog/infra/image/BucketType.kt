package dev.jombi.blog.infra.image

enum class BucketType { // https://youtrack.jetbrains.com/issue/KT-16304 Enum#name is not constant. so working with sealed thingy
    LOCAL, ORACLE;
}
