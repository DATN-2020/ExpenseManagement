package datn.datn_expansemanagement.kotlinex.number

fun Double?.getValueOrDefaultIsZero(): Double {
    return this ?: 0.0
}