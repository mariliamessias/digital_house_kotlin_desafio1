package br.com.digital_house.desafio

class Professor (var nome: String, var sobrenome : String, var tempoDeCasa: Int, var codigo: Int){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Professor
        if (codigo != other.codigo) return false

        return true
    }

    override fun toString(): String {
        return "Professor(nome='$nome', sobrenome='$sobrenome', tempoDeCasa=$tempoDeCasa, codigo=$codigo)"
    }


}