package com.example.listitemdetailapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listitemdetailapp.data.model.ListItem
import com.example.listitemdetailapp.databinding.ItemListBinding

/**
 * 列表适配器
 * 处理列表项显示和点击事件
 */
class ListAdapter(
    private val onItemClick: (ListItem) -> Unit
) : ListAdapter<ListItem, ListAdapter.ViewHolder>(ListItemDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    
    /**
     * ViewHolder类
     */
    inner class ViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        init {
            // 设置点击监听
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    onItemClick(item)
                }
            }
            
            // 设置长按监听（可选）
            binding.root.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    // 可以显示上下文菜单或执行其他操作
                    true
                } else {
                    false
                }
            }
        }
        
        fun bind(item: ListItem) {
            binding.apply {
                // 设置数据
                titleText.text = item.title
                descriptionText.text = item.getShortDescription()
                categoryText.text = item.category
                timeText.text = item.getFormattedCreatedAt()
                viewCountText.text = item.viewCount.toString()
                
                // 设置分类颜色
                val colorRes = item.getCategoryColorRes()
                val color = root.context.getColor(colorRes)
                categoryText.setTextColor(color)
                
                // 设置收藏状态
                favoriteIcon.setImageResource(
                    if (item.isFavorite) {
                        android.R.drawable.star_big_on
                    } else {
                        android.R.drawable.star_big_off
                    }
                )
                
                // 点击收藏图标切换收藏状态
                favoriteIcon.setOnClickListener {
                    // 这里可以触发ViewModel更新数据
                    // 暂时只更新UI
                    favoriteIcon.setImageResource(
                        if (item.isFavorite) {
                            android.R.drawable.star_big_off
                        } else {
                            android.R.drawable.star_big_on
                        }
                    )
                }
                
                // 设置点击反馈
                root.background = android.content.res.Resources.getSystem().getDrawable(
                    android.R.drawable.list_selector_background,
                    null
                )
            }
        }
    }
    
    /**
     * DiffUtil回调，优化列表更新
     */
    private class ListItemDiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }
}