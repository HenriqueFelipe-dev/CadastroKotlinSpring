package com.example.kotlincadastro.repository

import com.example.kotlincadastro.model.Cadastro
import org.springframework.data.jpa.repository.JpaRepository

interface CadastroRepository: JpaRepository<Cadastro, Long> {
}