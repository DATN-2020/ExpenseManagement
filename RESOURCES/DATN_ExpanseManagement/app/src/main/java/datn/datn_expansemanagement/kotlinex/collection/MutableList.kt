package datn.datn_expansemanagement.kotlinex.collection

fun <T> MutableList<T>?.getValueOrDefault(): MutableList<T> {
    return this ?: mutableListOf()
}