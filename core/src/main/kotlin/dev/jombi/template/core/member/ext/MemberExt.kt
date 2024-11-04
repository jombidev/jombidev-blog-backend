package dev.jombi.template.core.member.ext

import dev.jombi.template.core.member.entity.Member

fun Member.isSameMember(other: Member) = id == other.id
