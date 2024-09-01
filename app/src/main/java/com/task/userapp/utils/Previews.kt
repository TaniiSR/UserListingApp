package com.task.userapp.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Preview(
    name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
// Annotation to generate previews for the views in Light and Dark Mode

annotation class CombinedThemePreviews

@PreviewParameter(BooleanProvider::class)
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    backgroundColor = 0xFF000000,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
// Annotation to generate previews for the views in Light and Dark Mode with background

annotation class CombinedThemePreviewsWithBackground

// Simple boolean [true, false] parameter provider for compose previews

class BooleanProvider : PreviewParameterProvider<Boolean> {
    override val values = listOf(true, false).asSequence()
}

// Simple String parameter provider for compose previews

class StringProvider(text: String) : PreviewParameterProvider<String?> {
    override val values = listOf(text, "", null).asSequence()
}
