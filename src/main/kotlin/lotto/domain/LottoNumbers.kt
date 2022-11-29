package lotto.domain

@JvmInline
value class LottoNumbers(val value: List<LottoNumber>) {
    init {
        require(value.size == MAXIMUM_LOTTO_NUMBER_LENGTH) { "로또 번호는 6개의 숫자여야 합니다." }
        require(value.distinct().size == MAXIMUM_LOTTO_NUMBER_LENGTH) { "로또 번호는 중복될 수 없습니다." }
        require(value.sortedBy { it.value } == value) { "로또 번호는 오름차순으로 정렬되어야 합니다." }
    }

    fun getLottoRank(lottoNumbers: LottoNumbers): LottoRank {
        return LottoRank.valueOf(value.count { lottoNumbers.value.contains(it) })
    }

    companion object {
        const val MAXIMUM_LOTTO_NUMBER_LENGTH = 6
    }
}