package ghanam.com.univeo.extensions

import android.view.View
import android.view.animation.AnimationUtils
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import ghanam.com.univeo.R

object AnimationExt {
    fun View.slideIn(animTime: Long, startOffset: Long) {
        val slideIn = AnimationUtils.loadAnimation(context, R.anim.slide_in).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(slideIn)
    }


    fun View.clockwise(animTime: Long, startOffset: Long) {
        val clockwise = AnimationUtils.loadAnimation(context, R.anim.clockwise).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(clockwise)
    }

    fun View.zoom(animTime: Long, startOffset: Long) {
        val zoom = AnimationUtils.loadAnimation(context, R.anim.zoom).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(zoom)
    }

    fun View.blink(animTime: Long, startOffset: Long) {
        val blink = AnimationUtils.loadAnimation(context, R.anim.blink).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(blink)
    }

    fun View.fade(animTime: Long, startOffset: Long) {
        val fade = AnimationUtils.loadAnimation(context, R.anim.fade).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(fade)
    }

    fun View.slide(animTime: Long, startOffset: Long) {
        val slide = AnimationUtils.loadAnimation(context, R.anim.slide).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(slide)
    }

    fun View.move(animTime: Long, startOffset: Long) {
        val move = AnimationUtils.loadAnimation(context, R.anim.move).apply {
            duration = animTime
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffset
        }
        startAnimation(move)
    }
}