package virtualtrading.coinactions

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.Dispatchers
import virtualtrading.coinactions.databinding.FragmentBuyCoinBinding
import virtualtrading.data.firestore.api.datastore.UserPreferences

class BuyCoinFragment : Fragment(R.layout.fragment_buy_coin) {
    private var _binding: FragmentBuyCoinBinding? = null
    private val binding get() = _binding!!

    private lateinit var userPreferences: UserPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userPreferences = UserPreferences.getInstance(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBuyCoinBinding.bind(view)
        userPreferences.getUser().asLiveData(Dispatchers.IO).observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it!!.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}