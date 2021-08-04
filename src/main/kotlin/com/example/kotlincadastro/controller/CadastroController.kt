package com.example.kotlincadastro.controller

import com.example.kotlincadastro.model.Cadastro
import com.example.kotlincadastro.repository.CadastroRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CadastroController (private val cadastroRepository: CadastroRepository){

    @GetMapping("/cadastro")
    fun getAllCadastro(): List<Cadastro> =
        cadastroRepository.findAll()

    @PostMapping("/cadastro")
    fun createNewCadastro(@Validated @RequestBody cadastro: Cadastro): Cadastro =
        cadastroRepository.save(cadastro)

    @GetMapping("/cadastro/{id}")
    fun getCadastroById(@PathVariable(value = "id") cadastroId: Long): ResponseEntity<Cadastro>{
        return cadastroRepository.findById(cadastroId).map { cadastro ->  ResponseEntity.ok(cadastro)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/cadastro/{id}")
    fun updateCadastroById(@PathVariable(value = "id") cadastroId: Long,
    @Validated @RequestBody newCadastro: Cadastro): ResponseEntity<Cadastro>{
        return cadastroRepository.findById(cadastroId).map { existingCadastro ->
            val updateCadastro: Cadastro = existingCadastro
                .copy(nome = newCadastro.nome, dtNasc = newCadastro.dtNasc,
                    periodo = newCadastro.periodo, tipCurso = newCadastro.tipCurso)
            ResponseEntity.ok().body(cadastroRepository.save(updateCadastro))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/cadastro/{id}")
    fun deleteCadastroById(@PathVariable(value = "id") cadastroId: Long):ResponseEntity<Void>{
        return cadastroRepository.findById(cadastroId).map { cadastro ->
            cadastroRepository.delete(cadastro)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}