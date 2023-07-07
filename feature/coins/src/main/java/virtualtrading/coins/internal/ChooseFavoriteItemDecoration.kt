package virtualtrading.coins.internal

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseFavoriteItemDecoration(
    private val innerOffset: Int = 0,
    private val outerTopOffset: Int = 0,
    private val outerHorizontalOffset: Int = 0,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val currentPosition = parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return
        val spanCount = getTotalSpanCount(parent)

        with(outRect) {
            left = if (isFirstInRow(currentPosition, spanCount)) outerHorizontalOffset else innerOffset
            right = if (isLastInRow(currentPosition, spanCount)) outerHorizontalOffset else innerOffset
            top = if (isInTheFirstRow(currentPosition, spanCount)) outerTopOffset else innerOffset
            bottom = innerOffset
        }
    }

    private fun getTotalSpanCount(parent: RecyclerView): Int {
        val lm = parent.layoutManager
        return (lm as? GridLayoutManager)?.spanCount ?: 1
    }

    private fun isInTheFirstRow(position: Int, spanCount: Int): Boolean {
        return position < spanCount
    }

    private fun isFirstInRow(position: Int, spanCount: Int): Boolean {
        return position % spanCount == 0
    }

    private fun isLastInRow(position: Int, spanCount: Int): Boolean {
        return isFirstInRow(position + 1, spanCount)
    }
}