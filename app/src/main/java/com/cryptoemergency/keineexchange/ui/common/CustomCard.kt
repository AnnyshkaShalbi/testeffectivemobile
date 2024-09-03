package com.cryptoemergency.keineexchange.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cryptoemergency.keineexchange.providers.theme.Theme

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    innerPaddingCard: Modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Theme.colors.surface
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = innerPaddingCard) {
            content()
        }
    }
}