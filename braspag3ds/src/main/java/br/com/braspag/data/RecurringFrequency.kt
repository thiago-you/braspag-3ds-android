package br.com.braspag.data

enum class RecurringFrequency(val value: Int) {
    MONTHLY(1),
    BIMONTHLY(2),
    TRIMONTHLY(3),
    TRIANNUAL(4),
    SEMIANNUAL(6),
    YEARLY(12),
}
