package virtualtrading.coins.internal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.decode.SvgDecoder
import coil.load
import virtualtrading.coinranking.Coin
import virtualtrading.coinranking.CoinsImageFormat
import virtualtrading.coins.R
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
        binding.coinCurrentPrice.text = binding.root.context.getString(R.string.coin_price, item.price)
        binding.coinPriceChange.text = binding.root.context.getString(R.string.coin_percent_change, item.change)
        binding.coinIcon.load(item.iconUrl) {
            when (item.urlFormat) {
                CoinsImageFormat.SVG -> {
                    decoderFactory { result, options, _ -> SvgDecoder(result.source, options) }
                }
                else -> {}
            }
        }
        binding.coinPriceChange.setTextColor(
            if (item.isDecreased) {
                binding.root.context.getColor(virtualtrading.navigation.R.color.red)
            } else {
                binding.root.context.getColor(
                    virtualtrading.navigation.R.color.green
                )
            }
        )
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