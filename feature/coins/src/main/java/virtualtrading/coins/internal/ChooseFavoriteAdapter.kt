package virtualtrading.coins.internal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import virtualtrading.coinranking.RecommendedCoin
import virtualtrading.coins.databinding.AddToFavoriteListItemBinding

internal class ChooseFavoriteAdapter(
    private val onItemClicked: (RecommendedCoin) -> Unit,
) : ListAdapter<RecommendedCoin, ChooseFavoriteAdapter.ChooseFavoriteViewHolder>(ChooseFavoriteItemCallback) {

    companion object {
        const val IS_CHOOSED_KEY = "isChoosed"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseFavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = AddToFavoriteListItemBinding.inflate(layoutInflater, parent, false)
        return ChooseFavoriteViewHolder(view) { position ->
            onItemClicked(getItem(position))
        }
    }

    override fun onBindViewHolder(holder: ChooseFavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: ChooseFavoriteViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val payloadsBundle = payloads[0] as Bundle
            for (key in payloadsBundle.keySet()) {
                if (key.equals(IS_CHOOSED_KEY)) {
                    holder.bindChoice(payloadsBundle.getBoolean(key))
                }
            }
        }
    }

    class ChooseFavoriteViewHolder(private val binding: AddToFavoriteListItemBinding, onItemClicked: (Int) -> Unit) : ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(this@ChooseFavoriteViewHolder.adapterPosition)
            }
        }

        fun bind(item: RecommendedCoin) {
            binding.coinName.text = item.name
            binding.coinSymbol.text = item.symbol
            binding.coinCurrentPrice.text = binding.root.context.getString(virtualtrading.base.R.string.coin_price, item.price)
            binding.coinPriceChange.text = binding.root.context.getString(virtualtrading.base.R.string.coin_percent_change, item.change)
            binding.isChoosed.isChecked = item.isChoosed
            binding.coinPriceChange.setTextColor(
                if (item.isDecreased) {
                    binding.root.context.getColor(virtualtrading.base.R.color.red)
                } else {
                    binding.root.context.getColor(
                        virtualtrading.base.R.color.green
                    )
                }
            )
        }

        fun bindChoice(isChoosed: Boolean) {
            binding.isChoosed.isChecked = isChoosed
        }
    }
}

private object ChooseFavoriteItemCallback : DiffUtil.ItemCallback<RecommendedCoin>() {
    override fun areItemsTheSame(oldItem: RecommendedCoin, newItem: RecommendedCoin): Boolean {
        return oldItem.uuid == newItem.uuid
    }

    override fun areContentsTheSame(oldItem: RecommendedCoin, newItem: RecommendedCoin): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: RecommendedCoin, newItem: RecommendedCoin): Any {
        val payloadBundle = Bundle()
        if (oldItem.isChoosed != newItem.isChoosed) payloadBundle.putBoolean(ChooseFavoriteAdapter.IS_CHOOSED_KEY, newItem.isChoosed)
        return payloadBundle
    }
}




