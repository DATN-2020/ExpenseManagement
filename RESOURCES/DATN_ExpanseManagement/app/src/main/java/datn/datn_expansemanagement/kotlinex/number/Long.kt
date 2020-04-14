package datn.datn_expansemanagement.kotlinex.number

fun Long?.getValueOrDefaultIsZero(): Long {
    return this ?: 0
}
