package com.example.inventory.ui.item


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ItemSearchDestination : NavigationDestination {
    override val route = "item_search"
    override val titleRes = R.string.item_search_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSearchScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ItemSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        // Simulating initial data loading
        delay(1000)
        viewModel.searchItems("initial_query")
    }

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemSearchDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ItemSearchBody(
            searchUiState = viewModel.searchUiState,
            onSearchQueryChange = { query ->
                coroutineScope.launch {
                    // Simulating network delay
                    delay(300)
                    viewModel.searchItems(query)
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll()
                .fillMaxSize()
        )
    }
}


fun ItemSearchBody(searchUiState: SearchUiState, onSearchQueryChange: (String) -> Unit, modifier: Unit) {

}

private fun Unit.fillMaxSize() {

}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.verticalScroll() {

}

@Composable
fun ItemSearchBody(
    searchUiState: SearchUiState,
    onSearchQueryChange: (String) -> Unit,
    modifier: Modifier.Companion = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large))
    ) {
        // Search input field
        OutlinedTextField(
            value = searchUiState.query,
            //onValueChange = { onSearchQueryChange(it) },
            //label = { Text(stringResource(R.string.search_hint)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Display search results
        if (searchUiState.searchResults.isNotEmpty()) {
            Text(
                text = stringResource(R.string.search_results),
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_medium))
            )
            // TODO: Display the search results in a list or another appropriate way
        }

        // Button to trigger a new search
        Button(
            onClick = { /* TODO: Trigger a new search or perform additional actions */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.search_action))
        }
    }
}

private fun Any.isNotEmpty(): Boolean {

    return TODO("Provide the return value")
}

private fun Unit.background(background: Color): Any {
    TODO("Not yet implemented")
}

private fun <ColumnScope> ColumnScope.OutlinedTextField(value: Any, colors: TextFieldColors, keyboardOptions: KeyboardOptions, modifier: Modifier, singleLine: Boolean) {

}

@Preview(showBackground = true)
@Composable
private fun ItemSearchScreenPreview() {
    InventoryTheme {
        ItemSearchBody(
            searchUiState = SearchUiState(
                searchResults = listOf(
                    Item(1, "Item 1", 10.0, 5),
                    Item(2, "Item 2", 20.0, 8)
                )
            ),
            onSearchQueryChange = {}
        )
    }
}
