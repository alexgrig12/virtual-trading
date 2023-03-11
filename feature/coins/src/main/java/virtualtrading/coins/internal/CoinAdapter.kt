package virtualtrading.coins.internal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import virtualtrading.coinranking_domain.Coin
import virtualtrading.coins.databinding.CoinListItemBinding

internal class CoinAdapter() : ListAdapter<Coin, CoinViewHolder>(CoinItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CoinViewHolder(
            CoinListItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

internal class CoinViewHolder(private val binding: CoinListItemBinding) : ViewHolder(binding.root) {
    fun bind(item: Coin) {
        binding.coinName.text = item.name
        binding.coinSymbol.text = item.symbol
        binding.coinCurrentPrice.text = item.price
        binding.coinPriceChange.text = item.change
//        binding.coinIcon
    }
}

private object CoinItemCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.symbol == newItem.symbol
    }

}