package dev.jombi.blog.core.image.repository

import dev.jombi.blog.core.image.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ImageRepository : JpaRepository<Image, UUID>
