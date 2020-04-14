package datn.datn_expansemanagement.kotlinex.number

fun Float?.getValueOrDefaultIsZero(): Float {
    return this ?: 0f
}