package com.loanmanagementapp.utils

import androidx.compose.ui.tooling.preview.Preview

/**
 * [ResponsivenessCheckerPreview] annotation is designed to preview a Composable function
 * across various device screen sizes to ensure responsive UI design.
 *
 * This annotation includes:
 *
 * - **Phone - Extra Small**: 240dp x 432dp, high DPI (320).
 * - **Phone - Small**: 320dp x 640dp, high DPI (320).
 * - **Phone - Medium**: 360dp x 740dp, high DPI (320).
 * - **Phone - Large**: 400dp x 800dp, high DPI (320).
 * - **Foldable**: 720dp x 1280dp, high DPI (320).
 * - **Tablet - Medium**: 800dp x 1280dp, high DPI (320).
 * - **Tablet - Large**: 1000dp x 1600dp, high DPI (320).
 *
 * Use this annotation to check how your Composables behave on different devices,
 * ensuring adaptability and responsiveness across a range of screen sizes.
 */
@Preview(name = "Phone - Extra Small", device = "spec:width=240dp,height=432dp,dpi=320")
@Preview(name = "Phone - Small", device = "spec:width=320dp,height=640dp,dpi=320")
@Preview(name = "Phone - Medium", device = "spec:width=360dp,height=740dp,dpi=320")
@Preview(name = "Phone - Large", device = "spec:width=400dp,height=800dp,dpi=320")
@Preview(name = "Foldable", device = "spec:width=720dp,height=1280dp,dpi=320")
@Preview(name = "Tablet - Medium", device = "spec:width=800dp,height=1280dp,dpi=320")
@Preview(name = "Tablet - Large", device = "spec:width=1000dp,height=1600dp,dpi=320")
annotation class ResponsivenessCheckerPreview