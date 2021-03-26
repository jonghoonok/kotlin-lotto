package lotto.domain

data class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!(lotto.matchBonus(bonusNumber)))
    }
}
