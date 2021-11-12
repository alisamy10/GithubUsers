package com.example.githubusers.ui.features.userWebView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.githubusers.databinding.FragmentUserWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserWebViewFragment : Fragment() {

    private val args: UserWebViewFragmentArgs by navArgs()
    private lateinit var  binding  : FragmentUserWebViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUserWebViewBinding.inflate(inflater,container,false)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding. webView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.webViewUrl)
        }
    }

}