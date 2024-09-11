package com.example.fetchrewards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HiringList(viewModel: HiringViewModel = viewModel()) {
    val items by viewModel.hiringData

    val groups = items.groupBy { it.listId }.toSortedMap()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groups.forEach { group ->
            stickyHeader {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = group.key.toString(),
                        fontSize = 24.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            items(group.value.sortedBy { it.name }) { item ->
                HiringListItem(hiringItem = item)
            }

        }
    }
}

@Composable
fun HiringListItem(hiringItem: HiringItem) {
    Text(text = hiringItem.name!!, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
}