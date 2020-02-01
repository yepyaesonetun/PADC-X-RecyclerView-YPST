package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.models

import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.dataagents.HttpUrlConnectionDataAgentImpl
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.network.dataagents.NewsDataAgent

abstract class BaseModel (
    val mDataAgent : NewsDataAgent = HttpUrlConnectionDataAgentImpl
)