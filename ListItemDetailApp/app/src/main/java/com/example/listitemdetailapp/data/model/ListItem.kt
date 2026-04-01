package com.example.listitemdetailapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

/**
 * 列表项数据模型
 * 实现Parcelable接口以便在Activity之间传递
 */
@Parcelize
data class ListItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val category: String = "Uncategorized",
    val isFavorite: Boolean = false,
    val viewCount: Int = 0
) : Parcelable {
    
    companion object {
        /**
         * 创建示例数据列表
         */
        fun createSampleData(): List<ListItem> {
            return listOf(
                ListItem(
                    title = "Android Development Basics",
                    description = "Learn the fundamentals of Android development including Activities, Fragments, and UI components. Perfect for beginners.",
                    imageUrl = "https://example.com/android.jpg",
                    category = "Technology",
                    viewCount = 1250
                ),
                ListItem(
                    title = "Kotlin Programming Guide",
                    description = "Master Kotlin language features including extension functions, coroutines, and DSLs for advanced programming.",
                    imageUrl = "https://example.com/kotlin.jpg",
                    category = "Programming",
                    viewCount = 980
                ),
                ListItem(
                    title = "Material Design Guidelines",
                    description = "Understand Material Design principles to create beautiful and usable Android applications.",
                    imageUrl = "https://example.com/material.jpg",
                    category = "Design",
                    viewCount = 750
                ),
                ListItem(
                    title = "Architecture Patterns Best Practices",
                    description = "Learn modern Android architecture patterns like MVVM and MVI to build maintainable apps.",
                    imageUrl = "https://example.com/architecture.jpg",
                    category = "Architecture",
                    viewCount = 620
                ),
                ListItem(
                    title = "Performance Optimization Tips",
                    description = "Master Android app performance optimization techniques including memory management and startup optimization.",
                    imageUrl = "https://example.com/performance.jpg",
                    category = "Optimization",
                    viewCount = 540
                ),
                ListItem(
                    title = "Test Driven Development",
                    description = "Learn Android testing frameworks and practice test-driven development methodologies.",
                    imageUrl = "https://example.com/testing.jpg",
                    category = "Testing",
                    viewCount = 430
                ),
                ListItem(
                    title = "Jetpack Components Usage",
                    description = "Deep dive into Android Jetpack components including ViewModel, LiveData, and Room.",
                    imageUrl = "https://example.com/jetpack.jpg",
                    category = "Framework",
                    viewCount = 890
                ),
                ListItem(
                    title = "Compose UI Development",
                    description = "Learn to build declarative UIs with Jetpack Compose to improve development efficiency.",
                    imageUrl = "https://example.com/compose.jpg",
                    category = "UI",
                    viewCount = 1120
                )
            )
        }
        
        /**
         * 按分类筛选数据
         */
        fun filterByCategory(items: List<ListItem>, category: String): List<ListItem> {
            return items.filter { it.category == category }
        }
        
        /**
         * 获取所有分类
         */
        fun getAllCategories(items: List<ListItem>): List<String> {
            return items.map { it.category }.distinct()
        }
    }
    
    /**
     * 获取格式化后的创建时间
     */
    fun getFormattedCreatedAt(): String {
        return android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", createdAt).toString()
    }
    
    /**
     * 获取简短的描述（用于列表显示）
     */
    fun getShortDescription(maxLength: Int = 100): String {
        return if (description.length > maxLength) {
            description.substring(0, maxLength) + "..."
        } else {
            description
        }
    }
    
    /**
     * 获取分类颜色资源ID
     */
    fun getCategoryColorRes(): Int {
        return when (category) {
            "Technology" -> android.R.color.holo_blue_light
            "Programming" -> android.R.color.holo_green_light
            "Design" -> android.R.color.holo_orange_light
            "Architecture" -> android.R.color.holo_purple
            "Optimization" -> android.R.color.holo_red_light
            "Testing" -> android.R.color.holo_blue_dark
            "Framework" -> android.R.color.holo_green_dark
            "UI" -> android.R.color.holo_orange_dark
            else -> android.R.color.darker_gray
        }
    }
    
    /**
     * 增加查看次数
     */
    fun incrementViewCount(): ListItem {
        return copy(viewCount = viewCount + 1)
    }
    
    /**
     * 切换收藏状态
     */
    fun toggleFavorite(): ListItem {
        return copy(isFavorite = !isFavorite)
    }
}