package com.nemo.simkl.media.models

/**
 * Enum representing the interval for trending media.
 *
 * Possible values:
 * - today: Represents the current day.
 * - week: Represents the current week.
 * - month: Represents the current month.
 *
 * @property value The string representation of the interval.
 */
enum class Interval(val value: String) {
    TODAY("today"),
    WEEK("week"),
    MONTH("month");

    override fun toString(): String = value

    companion object {
        private val map = Interval.entries.associateBy(Interval::value)
        fun fromValue(value: String) = map[value]
    }
}
