package com.base.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build

class RippleUtil {

    companion object {

        fun getRippleStrokeDrawable(normalColor: Int, rippleColor: Int, cornerRadius: Float, strokeWidth: Int, strokeColor: Int): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable(ColorStateList.valueOf(rippleColor),
                        getStrokeDrawable(normalColor, cornerRadius, strokeWidth, strokeColor),
                        getDrawable(rippleColor, cornerRadius))
            } else {
                getDrawableLessThanLollipop(normalColor, cornerRadius, strokeWidth = strokeWidth, strokeColor = strokeColor)
            }
        }

        fun getRippleDrawable(normalColor: Int, rippleColor: Int, cornerRadius: Float, leftTopCornerRadius: Float,
                              rightTopCornerRadius: Float, leftBottomCornerRadius: Float, rightBottomCornerRadius: Float): Drawable {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable(ColorStateList.valueOf(rippleColor),
                        getDrawable(normalColor, cornerRadius, leftTopCornerRadius, rightTopCornerRadius, leftBottomCornerRadius, rightBottomCornerRadius),
                        getDrawable(rippleColor, cornerRadius, leftTopCornerRadius, rightTopCornerRadius, leftBottomCornerRadius, rightBottomCornerRadius))
            } else {
                getDrawableLessThanLollipop(normalColor, cornerRadius, leftTopCornerRadius, rightTopCornerRadius,
                        leftBottomCornerRadius, rightBottomCornerRadius)
            }
        }

        private fun getDrawableLessThanLollipop(color: Int, cornerRadius: Float = 0f, leftTopCornerRadius: Float = 0f,
                                                rightTopCornerRadius: Float = 0f, leftBottomCornerRadius: Float = 0f,
                                                rightBottomCornerRadius: Float = 0f, strokeWidth: Int = 0,
                                                strokeColor: Int = Color.TRANSPARENT): Drawable {
            val shape = GradientDrawable()
            shape.setColor(color)
            if (cornerRadius > 0f) {
                shape.cornerRadius = cornerRadius
            } else {
                shape.cornerRadii = floatArrayOf(leftTopCornerRadius, rightTopCornerRadius,
                        rightBottomCornerRadius, leftBottomCornerRadius)
            }
            shape.setStroke(strokeWidth, strokeColor)
            return shape
        }

        private fun getStrokeDrawable(color: Int, cornerRadius: Float, strokeWidth: Int, strokeColor: Int): Drawable {
            val shapeDrawable = GradientDrawable()
            shapeDrawable.cornerRadius = cornerRadius
            shapeDrawable.setColor(color)
            shapeDrawable.shape = GradientDrawable.RECTANGLE
            shapeDrawable.setStroke(strokeWidth, strokeColor)
            return shapeDrawable
        }

        private fun getDrawable(color: Int, cornerRadius: Float = 0f, leftTopCornerRadius: Float = 0f, rightTopCornerRadius: Float = 0f,
                                leftBottomCornerRadius: Float = 0f, rightBottomCornerRadius: Float = 0f): Drawable {
            var r: RoundRectShape? = null
            val outerRadii: FloatArray?
            if (cornerRadius > 0f) {
                outerRadii = FloatArray(8)
                outerRadii.fill(cornerRadius, 0, 8)
                r = RoundRectShape(outerRadii, null, null)
            } else if (leftTopCornerRadius != 0f || rightTopCornerRadius != 0f ||
                    leftBottomCornerRadius != 0f || rightBottomCornerRadius != 0f) {
                outerRadii = floatArrayOf(leftTopCornerRadius, leftTopCornerRadius, rightTopCornerRadius, rightTopCornerRadius,
                        rightBottomCornerRadius, rightBottomCornerRadius, leftBottomCornerRadius, leftBottomCornerRadius)
                r = RoundRectShape(outerRadii, null, null)
            }
            val shapeDrawable = ShapeDrawable(r)
            shapeDrawable.paint.color = color
            shapeDrawable.paint.isAntiAlias = true
            return shapeDrawable
        }

    }

}