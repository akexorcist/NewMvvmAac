package com.akexorcist.extralivedatatest.ui.sequentially.event

data class Step2Event(var name: String, var address: String, var gender: String) : BaseEvent(
    Event.STEP_1
)