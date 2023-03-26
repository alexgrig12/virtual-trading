package virtualtrading.coins.internal

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChooseFavoriteItemDecoration(private val innerOffset: Int, private val outerTopOffset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val adapter = parent.adapter ?: return

        val currentPosition = parent.getChildAdapterPosition(view).takeIf { it != RecyclerView.NO_POSITION } ?: return
        val spanCount = getTotalSpanCount(parent)

        with(outRect) {
            top = if (isInTheFirstRow(currentPosition, spanCount)) outerTopOffset else innerOffset
            left = innerOffset
            bottom = innerOffset
            right = innerOffset
        }
    }

    private fun getTotalSpanCount(parent: RecyclerView): Int {
        val lm = parent.layoutManager
        return (lm as? GridLayoutManager)?.spanCount ?: 1
    }

    private fun isInTheFirstRow(position: Int, spanCount: Int): Boolean {
        return position < spanCount
    }

}