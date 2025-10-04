package com.example.kotlin_nz.presentation.newsnavigator.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_nz.R
import com.example.kotlin_nz.presentation.Dimens

@Composable
fun NewsBottomBar(
    itemList: List<BottomBarItem>,
    selectedItem: Int
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = Dimens.SmallPadding3
    ) {
        itemList.forEachIndexed { index, bottomBarItem ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = bottomBarItem.onClick,
                icon = {
                    Column(
                        modifier = Modifier.padding(vertical = Dimens.SmallPadding3),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(bottomBarItem.icon),
                            contentDescription = bottomBarItem.name,
                            modifier = Modifier.size(Dimens.bottomBarIconSize)
                        )
                        Spacer(modifier = Modifier.height(Dimens.SmallPadding1))
                        Text(
                            text = bottomBarItem.name,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    selectedIndicatorColor = MaterialTheme.colorScheme.background,
                    disabledIconColor = Color.LightGray,
                    disabledTextColor = Color.LightGray
                )
            )
        }
    }

}

data class BottomBarItem(
    val name: String,
    @DrawableRes val icon: Int,
    val onClick: () -> Unit
)

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NewsBottomBarPreview() {
    val items = listOf(
        BottomBarItem(
            name = "Home",
            icon = R.drawable.ic_home,
            onClick = {}
        ),
        BottomBarItem(
            name = "Bookmarks",
            icon = R.drawable.ic_bookmark,
            onClick = {}
        )
    )

    NewsBottomBar(
        itemList = items,
        selectedItem = 0
    )
}
