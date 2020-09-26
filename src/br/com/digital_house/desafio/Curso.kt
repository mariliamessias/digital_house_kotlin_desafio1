package br.com.digital_house.desafio

class Curso (var nome : String, var codigo: Int){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Curso
        if (codigo != other.codigo) return false

        return true
    }

    override fun toString(): String {
        return "Curso(nome='$nome', codigo=$codigo)"
    }
}