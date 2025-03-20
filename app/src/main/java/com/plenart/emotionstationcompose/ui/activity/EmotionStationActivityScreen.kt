package com.plenart.emotionstationcompose.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AutoAdvancePager(pageItems: List<Color>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = { pageItems.size })
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false,
            ) { page ->
                Text(
                    text = "Page: $page",
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxSize()
                        .background(pageItems[page])
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(80.dp)
                .background(Color.Magenta)
                .fillMaxWidth()
        ) {
            TextButton(onClick = {
                coroutineScope.launch {
                    if(pagerState.currentPage > 0){
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            }) { Text("back") }
            PagerIndicator(pageItems.size, pagerState.currentPage)
            ElevatedButton(onClick = {
                coroutineScope.launch {
                    if(pagerState.currentPage < pageItems.size - 1){
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }) { Text("forward") }
        }
    }

}


@Composable
fun EmotionStationActivityScreen(
    imageUrl: String,
    selectedChildId: String,
    modifier: Modifier = Modifier
) {
    AutoAdvancePager(pageItems = listOf(Color.Black, Color.White, Color.Blue))

    /*
     Box(
         contentAlignment = Alignment.Center,
         modifier = Modifier.fillMaxSize()
     ) {
             AsyncImage(
                 model = imageUrl,
                 contentDescription = "Image"
             )

     }
     */
}

@Composable
fun PagerIndicator(pageCount: Int, currentPageIndex: Int, modifier: Modifier = Modifier) {
    Box(
        //    modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                //.fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color = if (currentPageIndex == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmotionStationActivityScreenPreview(modifier: Modifier = Modifier) {
    //EmotionStationActivityScreen(selectedChildId = "lalala")
}
