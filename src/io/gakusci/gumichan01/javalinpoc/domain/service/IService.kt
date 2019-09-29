package io.gakusci.gumichan01.javalinpoc.domain.service

import io.gakusci.gumichan01.javalinpoc.domain.model.DocumentEntry

interface IService {
    fun searchForResourceName(query: String): List<DocumentEntry>
}