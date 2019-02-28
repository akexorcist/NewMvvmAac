package com.akexorcist.extralivedatatest.ui.sequentially.event

data class Step1Event(var name: String, var address: String) : BaseEvent(
    Event.STEP_1
)