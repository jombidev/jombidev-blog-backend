package dev.jombi.template.core.image.repository

import dev.jombi.template.core.image.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ImageRepository : JpaRepository<Image, UUID>
