package com.aqrlei.open.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView

/**
 * @author aqrlei on 2018/11/9
 */

fun String.toSpannableString() = SpannableString(this)

/**
 * 设置前景色
 * @param color  [Int] 类型的颜色值
 * @param inclustart 要操作的字符串的起始位置，包含
 * @param excluend 要操作字符串的末尾位置，不包含
 */
fun SpannableString.foregroundColor(color: Int, inclustart: Int, excluend: Int): SpannableString {
    val colorSpan = ForegroundColorSpan(color)
    this.setSpan(colorSpan,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 设置背景色
 * */
fun SpannableString.backgroundColor(color: Int, inclustart: Int, excluend: Int): SpannableString {
    val colorSpan = BackgroundColorSpan(color)
    this.setSpan(colorSpan,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 改变字体大小
 * @param[size] 字体的相对大小
 * */
fun SpannableString.relativeSize(size: Float, inclustart: Int, excluend: Int): SpannableString {
    val sizeSpan = RelativeSizeSpan(size)
    this.setSpan(sizeSpan,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}
/**
 * 设置删除线
 */
fun SpannableString.strikeThrough(inclustart: Int, excluend: Int): SpannableString {
    val strikeSpan = StrikethroughSpan()
    this.setSpan(strikeSpan,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 设置下滑线
 */
fun SpannableString.underline(inclustart: Int, excluend: Int): SpannableString {
    val span = UnderlineSpan()
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 设置上标
 */
fun SpannableString.superscript(inclustart: Int, excluend: Int): SpannableString {
    val span = SuperscriptSpan()
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}
/**
 * 设置下标
 */
fun SpannableString.subscript(inclustart: Int, excluend: Int): SpannableString {
    val span = SubscriptSpan()
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}
/**
 * 改变字体样式
 * @param typeFace  [android.graphics.Typeface]
 * */
fun SpannableString.style(typeFace: Int, inclustart: Int, excluend: Int): SpannableString {
    val span = StyleSpan(typeFace)
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}


/**
 * 设置点击事件
 * @param textView 要设置字符串的TextView
 * @param action  点击的响应事件
 * @param textHighlightColor 响应点击的文字的颜色
 * 使用ClickableSpan的文本如果想真正实现点击作用，
 * 必须为TextView设置setMovementMethod方法，否则点击不会响应，
 * */
fun SpannableString.clickable(textView: TextView, action: () -> Unit, inclustart: Int, excluend: Int, textHighlightColor: Int): SpannableString {
    val clickActionSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            action.invoke()
        }
    }
    this.setSpan(clickActionSpan, inclustart, excluend, SpannableString.SPAN_INCLUSIVE_INCLUSIVE)
    textView.highlightColor = textHighlightColor
    textView.movementMethod = LinkMovementMethod.getInstance()
    return this
}

/**
 * 设置超链接
 * @param url 字符串对应的链接
 */
fun SpannableString.url(textView: TextView, url: String, inclustart: Int, excluend: Int, textHighlightColor: Int): SpannableString {
    textView.movementMethod = LinkMovementMethod.getInstance()
    textView.highlightColor = textHighlightColor
    val span = URLSpan(url)
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 引用竖线效果
 * @param quoteColor  竖线的颜色
 * @param stripeWidth 竖线的宽度，单位: DP
 * @param gapWidth 竖线与文字的距离，单位: DP
 */
fun SpannableString.quote(quoteColor: Int,stripeWidth:Float,gapWidth:Float = 0F, inclustart: Int, excluend: Int): SpannableString {
    val span = QuoteSpan(quoteColor,DensityUtil.dip2px(stripeWidth),DensityUtil.dip2px(gapWidth))
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 文字缩放
 * @param scale 缩放倍数
 */
fun SpannableString.scaleX(scale: Float, inclustart: Int, excluend: Int): SpannableString {
    val span = ScaleXSpan(scale)
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this

}

/**
 * 文本缩进
 * @param first 第一行的缩进
 * @param rest  其余行的缩进
 * */
fun SpannableString.leadingMargin(first: Float, rest: Float): SpannableString {
    this.setSpan(LeadingMarginSpan.Standard(DensityUtil.dip2px(first), DensityUtil.dip2px(rest)), 0, this.length, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 插入图片
 * @param[bitmap] 要插入的图片
 * @param[margin] 图片与文字的距离，单位: DP
 */
fun SpannableString.imageMargin(bitmap: Bitmap, margin: Float, inclustart: Int, excluend: Int): SpannableString {

    val span = IconMarginSpan(bitmap,DensityUtil.dip2px(margin))
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 插入图片
 * @param[drawable] 要插入的图片
 */
fun SpannableString.imageMargin(drawable: Drawable, margin: Float, inclustart: Int, excluend: Int): SpannableString {
    val span = DrawableMarginSpan(drawable,DensityUtil.dip2px(margin))
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}

/**
 * 标签圆点效果
 * @param[gabWidth] 与文字的距离，单位: DP
 */
fun SpannableString.bullet(gabWidth: Float, color: Int, inclustart: Int, excluend: Int): SpannableString {
    val span = BulletSpan(DensityUtil.dip2px(gabWidth),color)
    this.setSpan(span,inclustart,excluend,SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
    return this
}
