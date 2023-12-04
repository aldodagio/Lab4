package com.example.inventory.ui.item
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.Flow


/**
 * ViewModel for searching and retrieving items from the Room database.
 */
class ItemSearchViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    private val _searchUiState = mutableStateOf(SearchUiState())

    val searchUiState: SearchUiState by _searchUiState

    /**
     * Searches for items based on the provided query.
     */
    fun searchItems(query: String) {
        // Perform the search operation using the repository
        val searchResults = itemsRepository.searchItems(query)

        // Update the UI state with the search results
        _searchUiState.value = SearchUiState(searchResults = searchResults)
    }


}


data class SearchUiState(
    val searchResults: Flow<List<SearchDetails>>,
    val isEntryValid: Boolean = false
) {
    fun collectAsState() {

        return TODO("Provide the return value")
    }

    val query: String = ""
}
data class SearchDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

