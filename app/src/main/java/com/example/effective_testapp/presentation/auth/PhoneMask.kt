package com.example.effective_testapp.presentation.auth

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.min

class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    //private val maxLength = mask.count { it == maskNumber }
    private val maxLength = 13

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length >= maxLength) text.take(maxLength) else text

        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        return maskNumber == other.maskNumber
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(private val mask: String, private val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount && i < mask.length) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return min(offset + noneDigitCount, mask.length)
    }

    override fun transformedToOriginal(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount && i < mask.length) {
            if (mask[i++] != numberChar) noneDigitCount++
        }
        return min(offset - noneDigitCount, mask.length)
    }
}

object PhoneHintVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val newText = buildAnnotatedString {
            if (text.isNotEmpty()) {
                append("+7")
                append(text)
            } else {
                append("+7")
            }
        }
        return TransformedText(newText, PhoneOffsetMappers)
    }
}

object PhoneOffsetMappers : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return offset + 2
    }

    override fun transformedToOriginal(offset: Int): Int {
        return offset - 2
    }
}