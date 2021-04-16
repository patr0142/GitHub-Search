package bob.pat.final2020

// Created by: Katelyn Patrick December 11 2020

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(min: Int, max: Int) : InputFilter {
    private var min = 0
    private var max = 0

    init {
        this.min = min
        this.max = max
    }

    override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            destination: Spanned,
            destinationStart: Int,
            destinationEnd: Int
    ): CharSequence? {
        try {
            val input = (destination.subSequence(0, destinationStart).toString() + source +
                    destination.subSequence(destinationEnd, destination.length)).toInt()
            if (isInRange(min, max, input)) {
                return null
            }
        } catch (ex: NumberFormatException) {
            // just ignore the exception
        }
        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}


