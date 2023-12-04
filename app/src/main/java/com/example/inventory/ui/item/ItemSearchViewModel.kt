import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

/**
 * ViewModel for searching and retrieving items from the Room database.
 */
class ItemSearchViewModel(private val itemsRepository: ItemsRepository) : ViewModel() {

    /**
     * Holds the current search results and UI state.
     */
    var searchUiState by mutableStateOf(SearchUiState())
        private set

    /**
     * Searches for items based on the provided query.
     */
    fun searchItems(query: String) {
        // Perform the search operation using the repository
        val searchResults = itemsRepository.searchItems(query)

        // Update the UI state with the search results
        searchUiState = SearchUiState(searchResults = searchResults as SearchDetails)
    }
}

data class SearchUiState(
    val searchResults: List<Item> = SearchDetails(),
    val isEntryValid: Boolean = false
) {
    val query: Any
        get() {
            TODO()
        }
}

data class SearchDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
) : Flow<List<Item>>, List<Item> {
    override suspend fun collect(collector: FlowCollector<List<Item>>) {
        TODO("Not yet implemented")
    }

    override val size: Int
        get() = TODO("Not yet implemented")

    override fun get(index: Int): Item {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<Item> {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<Item> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<Item> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<Item> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: Item): Int {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: Item): Int {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<Item>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: Item): Boolean {
        TODO("Not yet implemented")
    }
}
