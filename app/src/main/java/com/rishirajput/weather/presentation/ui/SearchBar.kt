package com.rishirajput.weather.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishirajput.weather.R
import highlightedBackGround

@Composable
fun SearchBar(
    query: String,
    placeholderText: String,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp)
            .highlightedBackGround()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = query,
            onValueChange = {
                onSearch(it)
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.isEmpty()) {
                        PlaceHolderText(text = placeholderText)
                    }
                    innerTextField()
                }
            }
        )
        SearchIcon()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    SearchBar("", stringResource(id = R.string.search_location), onSearch = {})
}