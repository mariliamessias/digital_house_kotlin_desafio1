package br.com.digital_house.desafio

import sun.invoke.empty.Empty
import java.lang.RuntimeException
import java.util.*

class DigitalHouseManager(
    val alunos: MutableList<Aluno>,
    val matriculas: MutableList<Matricula>,
    val cursos: MutableList<Curso>,
    val professores: MutableList<Professor>
) {

    fun registrarCurso(nome: String, codigoCurso: Int, quantidadeMaximaDeAlunos: Int) {

        val curso = Curso(nome, codigoCurso)
        curso.quantidadeMaximaAlunos = quantidadeMaximaDeAlunos
        cursos.add(curso)

    }

    fun excluirCurso(codigoCurso: Integer) {
        cursos
            .stream()
            .filter { a -> a.codigo.equals(codigoCurso) }
            .findFirst()
            .map { i ->
                {
                    cursos.remove(i)
                    println("Curso Excluído com sucesso")
                }
            }
            .orElse({ println("Curso não excluído pois não foi localizado") })
    }

    fun registrarProfessorAdjunto(nome: String, sobrenome: String, codigoProfessor: Int, quantidadeDeHoras: Int) {
        professores.add(ProfessorAdjunto(quantidadeDeHoras, nome, sobrenome, 0, codigoProfessor))
    }

    fun registrarProfessorTitular(nome: String, sobrenome: String, codigoProfessor: Int, especialidade: String) {
        professores.add(ProfessorTitular(especialidade, nome, sobrenome, 0, codigoProfessor))
    }

    fun excluirProfessor(codigoProfessor: Int) {

        professores
            .stream()
            .filter { a -> a.codigo.equals(codigoProfessor) }
            .findFirst()
            .map { i ->
                {
                    professores.remove(i)
                    println("Professor Excluído com sucesso")
                }
            }
            .orElse({ println("Professor não excluído pois não foi localizado") })
    }

    fun matricularAluno(nome: String, sobrenome: String, codigoAluno: Int) {
        alunos.add(Aluno(nome, sobrenome, codigoAluno))
    }

    fun matricularAluno(codigoAluno: Int, codigoCurso: Int) {

        val cursoPretendido = cursos
            .stream()
            .filter { a -> a.codigo == codigoCurso }
            .findFirst()
            .get()

        val alunoPretendido = alunos
            .stream()
            .filter { al -> al.codigo == codigoAluno }
            .findFirst()
            .get()

        (if (cursoPretendido != null && alunoPretendido != null) {

            val matriculaPermitida =
                cursoPretendido.alunosMatriculados.stream()
                    .noneMatch { al -> al.codigo == codigoAluno }

            if (matriculaPermitida) {
                matriculas.add(Matricula(alunoPretendido, cursoPretendido))
                println("Matricula Realizada com sucesso")
            } else {
                println("Não há vagas disponíveis")
            }

        } else {
            println("aluno ou curso não localizado")
        })

    }

    fun alocarProfessores(codigoCurso: Int, codigoProfessorTitular: Int, codigoProfessorAdjunto: Int) {

        val professorTitular = professores
            .stream()
            .filter { a -> a.codigo == codigoProfessorTitular }
            .findFirst()
            .get()

        val professorAdjunto = professores
            .stream()
            .filter { a -> a.codigo == codigoProfessorAdjunto }
            .findFirst()
            .get()

        if (professorTitular != null && professorAdjunto != null) {

            val cursoAdjunto: Curso = cursos.stream()
                .filter { c -> c.professorAdjunto.codigo == professorAdjunto.codigo }
                .filter { p -> p.codigo == codigoCurso }
                .findFirst()
                .get()

            val cursoTitular: Curso = cursos.stream()
                .filter { c -> c.professorTitular.codigo == professorTitular.codigo }
                .filter { p -> p.codigo == codigoCurso }
                .findFirst()
                .get()

            if (cursoAdjunto == null && cursoTitular == null) {
                val cursoMatricular = cursos.stream().filter { c -> c.codigo == codigoCurso }
                    .findFirst()
                    .get()

                if (cursoMatricular != null) {
                    cursoMatricular.professorTitular = professorTitular as ProfessorTitular
                    cursoMatricular.professorAdjunto = professorTitular as ProfessorAdjunto

                }

            }
        }


    }
}
