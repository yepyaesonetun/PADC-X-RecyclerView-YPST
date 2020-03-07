package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.mvi.mvibase

import io.reactivex.Observable


interface MVIViewModel<S: MVIState, I: MVIIntent> {
    fun processIntent(intents : Observable<I>)
    fun states() : Observable<S>
}