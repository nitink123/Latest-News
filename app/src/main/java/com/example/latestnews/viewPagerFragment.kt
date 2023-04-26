package com.example.latestnews

import android.app.Application
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.latestnews.databinding.TablayoutfragmentBinding


class ViewPagerFragment : Fragment(),handleonclick{

    private lateinit var binding: TablayoutfragmentBinding
    private lateinit var viewModel : newsVM
    private lateinit var newsAdapter: NewsAdapter
    private var list = mutableListOf<Article>()

    companion object {

        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String): ViewPagerFragment {
            val fragment = ViewPagerFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY, category)
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TablayoutfragmentBinding.inflate(inflater, container, false)
       newsAdapter = NewsAdapter(requireContext(),list,this)
        viewModel = newsVM(requireActivity().application)
        viewModel = ViewModelProvider(this)[newsVM::class.java]
        val category = arguments?.getString(ARG_CATEGORY)


        binding.recylerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recylerView.adapter = newsAdapter
        viewModel.getNewsCategoryWise(category.toString())

        viewModel.newsResponseCategory.observe(viewLifecycleOwner) { result->
            val ans = result.body()
            if (ans != null) {
                  binding.progressCircular.visibility = View.GONE
                list.addAll(ans.articles)
                newsAdapter.notifyDataSetChanged()
            } else {
                //  Buil
                binding.nothingTv.text = "This is the $category fragment and we are not getting response"
//                Toast.makeText(this,"response is not generated", Toast.LENGTH_LONG)
                // Handle null result, if needed
            }

        }
        binding.nothingTv.text = "This is the $category fragment"
        return binding.root
    }


    fun onbackPressed(){
        binding.webviewDesc.visibility = View.GONE
        binding.webviewDesc.removeAllViews()
    }





    override fun handleclick(url: String) {
        binding.webviewDesc.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressCircular.visibility = View.VISIBLE// show the progress bar
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressCircular.visibility = View.GONE
             //   binding.progressBar.visibility = View.GONE // hide the progress bar
            }
        }

        binding.webviewDesc.visibility = View.VISIBLE
        binding.webviewDesc.loadUrl(url)
    }


}
