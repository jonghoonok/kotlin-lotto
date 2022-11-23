package lotto.domain

class LottoTickets(ticketCount: Int) {
    val tickets: List<LottoTicket> = List(ticketCount) {
        LottoTicket(LottoAutoGenerateStrategy())
    }
}