package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase

import io.reactivex.Observable


interface MVIView<I : MVIIntent, in S : MVIState> {
    fun render(states : S)
    fun intents() : Observable<I>
}