package datn.datn_expansemanagement.kotlinex.boolean

fun Boolean?.getValueOrDefault(): Boolean {
    return this ?: false
}