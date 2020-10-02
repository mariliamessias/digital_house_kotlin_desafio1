package br.com.digital_house.desafio

import java.util.*

class Curso(var nome: String, var codigo: Int) {
    lateinit var professorTitular: ProfessorTitular
    lateinit var professorAdjunto: ProfessorAdjunto
    var quantidadeMaximaAlunos: Int = 0
    lateinit var alunosMatriculados: MutableList<Aluno>

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

    fun adicionarUmAluno(umAluno: Aluno): Boolean {
        return alunosMatriculados
            .stream()
            .anyMatch { a -> a.equals(umAluno) }
                && alunosMatriculados.size < quantidadeMaximaAlunos
    }

    fun excluirAluno(umAluno: Aluno) {
        alunosMatriculados
            .stream()
            .filter { a -> a.equals(umAluno) }
            .findFirst()
            .map { i ->
                {
                    alunosMatriculados.remove(i)
                    println("Aluno Excluído com sucesso")
                }
            }
            .orElse({ println("Aluno não excluído pois não foi localizado") })

    }

    private fun <T> Optional<T>.orElse(s: String) {

    }
}



