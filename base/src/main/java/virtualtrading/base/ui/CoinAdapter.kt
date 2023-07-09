package virtualtrading.base.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.decode.SvgDecoder
import coil.load
import virtualtrading.base.R
import virtualtrading.base.databinding.CoinListItemBinding
import virtualtrading.coinranking.Coin
import virtualtrading.coinranking.CoinsImageFormat

class CoinAdapter(
    private val onCoinClicked: (coinId: String) -> Unit,
) : ListAdapter<Coin, CoinViewHolder>(CoinItemCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CoinViewHolder(
            CoinListItemBinding.inflate(layoutInflater, parent, false)
        ) { position ->
            onCoinClicked(getItem(position).uuid)
        }
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class CoinViewHolder(private val binding: CoinListItemBinding, onCoinClicked: (position: Int) -> Unit) : ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            onCoinClicked(this@CoinViewHolder.adapterPosition)
        }
    }

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
                binding.root.context.getColor(R.color.red)
            } else {
                binding.root.context.getColor(
                    R.color.green
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