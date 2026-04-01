package com.example.listitemdetailapp.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listitemdetailapp.R
import com.example.listitemdetailapp.data.model.ListItem
import com.example.listitemdetailapp.databinding.ActivityDetailBinding
import com.example.listitemdetailapp.ui.list.ListActivity

/**
 * 详情页面Activity
 * 显示列表项的完整内容
 */
class DetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityDetailBinding
    private var listItem: ListItem? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 启用边缘到边缘显示
        enableEdgeToEdge()
        
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 设置系统栏边距
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // 从Intent获取数据
        listItem = intent.getParcelableExtra(ListActivity.EXTRA_LIST_ITEM)
        
        setupToolbar()
        setupViews()
        displayData()
    }
    
    /**
     * 设置工具栏
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        
        // 设置工具栏标题
        supportActionBar?.title = listItem?.title ?: getString(R.string.detail_title)
    }
    
    /**
     * 设置视图
     */
    private fun setupViews() {
        // 设置收藏按钮点击监听
        binding.favoriteButton.setOnClickListener {
            listItem?.let { item ->
                // 切换收藏状态
                val newItem = item.toggleFavorite()
                listItem = newItem
                updateFavoriteButton(newItem.isFavorite)
                
                // 这里可以更新数据源
            }
        }
        
        // 设置分享按钮点击监听
        binding.shareButton.setOnClickListener {
            shareContent()
        }
    }
    
    /**
     * 显示数据
     */
    private fun displayData() {
        listItem?.let { item ->
            // 显示标题
            binding.titleText.text = item.title
            
            // 显示完整描述
            binding.descriptionText.text = item.description
            
            // 显示分类
            binding.categoryText.text = item.category
            val colorRes = item.getCategoryColorRes()
            val color = getColor(colorRes)
            binding.categoryText.setTextColor(color)
            
            // 显示创建时间
            binding.timeText.text = getString(R.string.created_at_format, item.getFormattedCreatedAt())
            
            // 显示查看次数
            binding.viewCountText.text = getString(R.string.view_count_format, item.viewCount)
            
            // 更新收藏按钮状态
            updateFavoriteButton(item.isFavorite)
            
            // 如果有图片URL，可以在这里加载图片
            item.imageUrl?.let { imageUrl ->
                // 使用图片加载库加载图片
                // Glide.with(this).load(imageUrl).into(binding.itemImage)
                binding.itemImage.visibility = android.view.View.VISIBLE
            } ?: run {
                binding.itemImage.visibility = android.view.View.GONE
            }
        } ?: run {
            // 如果没有数据，显示错误信息
            showErrorState()
        }
    }
    
    /**
     * 更新收藏按钮状态
     */
    private fun updateFavoriteButton(isFavorite: Boolean) {
        val iconRes = if (isFavorite) {
            android.R.drawable.star_big_on
        } else {
            android.R.drawable.star_big_off
        }
        val textRes = if (isFavorite) {
            R.string.remove_favorite
        } else {
            R.string.add_favorite
        }
        
        binding.favoriteButton.setIconResource(iconRes)
        binding.favoriteButton.text = getString(textRes)
    }
    
    /**
     * 分享内容
     */
    private fun shareContent() {
        listItem?.let { item ->
            val shareIntent = android.content.Intent().apply {
                action = android.content.Intent.ACTION_SEND
                type = "text/plain"
                putExtra(
                    android.content.Intent.EXTRA_TEXT,
                    "${item.title}\n\n${item.description}\n\n${item.category}"
                )
                putExtra(android.content.Intent.EXTRA_SUBJECT, item.title)
            }
            
            startActivity(android.content.Intent.createChooser(shareIntent, getString(R.string.share_via)))
        }
    }
    
    /**
     * 显示错误状态
     */
    private fun showErrorState() {
        binding.contentLayout.visibility = android.view.View.GONE
        binding.errorLayout.visibility = android.view.View.VISIBLE
        
        binding.errorRetryButton.setOnClickListener {
            // 重试加载数据
            listItem = intent.getParcelableExtra(ListActivity.EXTRA_LIST_ITEM)
            if (listItem != null) {
                binding.contentLayout.visibility = android.view.View.VISIBLE
                binding.errorLayout.visibility = android.view.View.GONE
                displayData()
            }
        }
    }
    
    /**
     * 处理返回按钮点击
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    
    /**
     * 添加返回动画
     */
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}