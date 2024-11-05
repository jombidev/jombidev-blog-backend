package dev.jombi.blog.common.multipart

import org.apache.tika.config.TikaConfig
import org.apache.tika.detect.DefaultDetector
import org.apache.tika.metadata.Metadata
import java.io.ByteArrayInputStream

fun guessMediaType(bytes: ByteArray) = DefaultDetector().detect(
    ByteArrayInputStream(bytes),
    Metadata()
).toString()

fun guessExtension(ogName: String?, bytes: ByteArray) =
    ogName?.substring(ogName.lastIndexOf('.') + 1) ?: TikaConfig.getDefaultConfig().mimeRepository.forName(
        guessMediaType(bytes)
    ).extension
