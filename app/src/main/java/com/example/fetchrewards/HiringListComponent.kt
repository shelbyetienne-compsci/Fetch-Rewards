package com.example.fetchrewards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HiringList(viewModel: HiringViewModel=viewModel()){
    val items by viewModel.hiringData

    val groups = items.groupBy { it.listId }.toSortedMap()

    LazyColumn {
        groups.forEach{ group ->
            stickyHeader {
                Column {
                    Text(text = group.key.toString())
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
    Text(text = hiringItem.name!!)
}