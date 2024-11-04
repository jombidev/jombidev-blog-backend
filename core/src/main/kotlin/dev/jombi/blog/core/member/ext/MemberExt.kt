package dev.jombi.blog.core.member.ext

import dev.jombi.blog.core.member.entity.Member

fun Member.isSameMember(other: Member) = id == other.id
