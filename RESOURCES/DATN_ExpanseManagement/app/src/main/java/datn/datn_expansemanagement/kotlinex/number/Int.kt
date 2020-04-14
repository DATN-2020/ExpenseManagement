package datn.datn_expansemanagement.kotlinex.number

fun Int?.getValueOrDefaultIsZero(): Int {
    return this ?: 0
}