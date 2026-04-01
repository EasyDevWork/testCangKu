package com.example.listitemdetailapp.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listitemdetailapp.R
import com.example.listitemdetailapp.data.model.ListItem
import com.example.listitemdetailapp.databinding.ActivityListBinding
import com.example.listitemdetailapp.ui.detail.DetailActivity

/**
 * 列表页面Activity
 * 显示项目列表，处理点击事件打开详情页
 */
class ListActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: ListAdapter
    
    companion object {
        const val EXTRA_LIST_ITEM = "extra_list_item"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 启用边缘到边缘显示
        enableEdgeToEdge()
        
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // 设置系统栏边距
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        setupToolbar()
        setupRecyclerView()
        loadData()
    }
    
    /**
     * 设置工具栏
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)
        
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
    
    /**
     * 设置RecyclerView
     */
    private fun setupRecyclerView() {
        adapter = ListAdapter { item ->
            // 处理列表项点击，导航到详情页
            navigateToDetail(item)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = this@ListActivity.adapter
            setHasFixedSize(true)
            
            // 添加项目装饰（可选）
            val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
            addItemDecoration(ItemSpacingDecoration(spacing))
        }
    }
    
    /**
     * 加载数据
     */
    private fun loadData() {
        // 这里可以从ViewModel获取数据
        // 暂时使用示例数据
        val sampleData = ListItem.createSampleData()
        adapter.submitList(sampleData)
        
        // 更新空状态显示
        binding.emptyStateView.visibility = if (sampleData.isEmpty()) {
            android.view.View.VISIBLE
        } else {
            android.view.View.GONE
        }
    }
    
    /**
     * 导航到详情页
     */
    private fun navigateToDetail(item: ListItem) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_LIST_ITEM, item)
        }
        startActivity(intent)
        
        // 添加页面切换动画
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
    
    /**
     * 处理返回按钮点击
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
    
    /**
     * RecyclerView项目间距装饰器
     */
    private class ItemSpacingDecoration(private val spacing: Int) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: android.graphics.Rect,
            view: android.view.View,
            parent: androidx.recyclerview.widget.RecyclerView,
            state: androidx.recyclerview.widget.RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            
            val position = parent.getChildAdapterPosition(view)
            val itemCount = state.itemCount
            
            // 第一个项目顶部间距
            if (position == 0) {
                outRect.top = spacing
            }
            
            // 所有项目底部间距
            outRect.bottom = spacing
            
            // 左右间距
            outRect.left = spacing
            outRect.right = spacing
        }
    }
}