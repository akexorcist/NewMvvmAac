package com.akexorcist.extralivedatatest.ui.usecase.kind

import com.akexorcist.extralivedatatest.domain.UseCase

class AnySimpleUseCase constructor() : UseCase<String, String>() {
    override fun execute(parameters: String): String {
        return parameters
    }
}