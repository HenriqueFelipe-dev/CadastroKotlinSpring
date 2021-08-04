package com.example.kotlincadastro.model

import com.sun.istack.NotNull
import org.hibernate.annotations.CreationTimestamp
import java.sql.Date
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.persistence.*

@Entity
data class Cadastro (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get: NotNull
    val nome: String = "",

    @get: NotNull
    val dtNasc: String = "",

    @get: NotNull
    val periodo: String = "",

    @get: NotNull
    val tipCurso: String = "",

    @get: NotNull
    var dataCadastro: String = ""


        ) {

    @PrePersist
    fun persistData(){
        if(dataCadastro == ""){
            dataCadastro = LocalDateTime.now()
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
                .toString()
        }
    }

}