package lotto.domain

import java.util.SortedSet

/**
 * 로또 1게임
 */
data class LottoNumberPackage(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_GAME_NUMBER_COUNT) { INVALID_LOTTO_GAME_NUMBER_COUNT_MESSAGE }
    }

    fun size(): Int {
        return numbers.size
    }

    fun getSortedNumbers(): SortedSet<Int> {
        return numbers
            .map { it.value }
            .toSortedSet(compareBy { it })
    }

    fun getRankKey(winningInfo: WinningInfo): LottoResultRankKey {
        val matchedCount = getMatchedCount(winningInfo.winningNumberPackage)
        return LottoResultRankKey(
            matchedCount,
            matchedCount.shouldCheckBonusNumber() && matchedBonusNumber(winningInfo.bonusNumber)
        )
    }

    private fun getMatchedCount(winningNumberPackage: LottoNumberPackage): MatchedCount {
        return MatchedCount.of(numbers.intersect(winningNumberPackage.numbers).size)
    }

    private fun matchedBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.contains(bonusNumber)
    }

    fun getPrizeMoney(winningInfo: WinningInfo): Long {
        return LottoResultRank.getRank(getRankKey(winningInfo)).prizeMoney
    }

    companion object {
        const val LOTTO_GAME_NUMBER_COUNT = 6
        private const val INVALID_LOTTO_GAME_NUMBER_COUNT_MESSAGE =
            "잘못된 로또 번호 개수입니다.(번호 ${LOTTO_GAME_NUMBER_COUNT}개 입력)"
    }
}