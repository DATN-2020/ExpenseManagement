package datn.datn_expansemanagement.core.base.domain.mapper

interface Mapper<in I, out O> {
    fun map(input: I) : O
}