package com.zref.masakapa.splash

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.zref.masakapa.BaseFragment
import com.zref.masakapa.R
import com.zref.masakapa.databinding.FragmentSplashBinding
import android.view.*
import androidx.core.content.ContextCompat


class SplashFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)

        return FragmentSplashBinding.inflate(inflater, container, false).root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper())
            .postDelayed({
                findNavController()
                    .navigate(R.id.splashFragment_to_listFoodFragment)
            }, 1500)
    }
}