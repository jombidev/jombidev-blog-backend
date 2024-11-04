package dev.jombi.template.core.article.entity

import dev.jombi.template.core.common.entity.BaseTimeEntity
import dev.jombi.template.core.common.entity.FetchableId.FetchableLong
import dev.jombi.template.core.image.entity.Image
import dev.jombi.template.core.member.entity.Member
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: FetchableLong = FetchableLong.NULL,

    @Column(nullable = false, length = 128)
    val title: String,

    @Column(nullable = false, length = 65535)
    val content: String, // md

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    val author: Member,

    @OneToMany(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "article_id")
    val images: Set<Image>
) : BaseTimeEntity()
