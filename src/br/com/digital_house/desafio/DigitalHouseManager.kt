package br.com.digital_house.desafio

class DigitalHouseManager() {

    val alunos = mutableListOf<Aluno>()
    val matriculas = mutableListOf<Matricula>()
    val cursos = mutableListOf<Curso>()
    val professores = mutableListOf<Professor>()

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
        println("Aluno Matriculado com sucesso: $nome")
    }

    fun matricularAluno(codigoAluno: Int, codigoCurso: Int) {

        val alunoExistente = alunos.stream().anyMatch { a -> a.codigo == codigoAluno }

        if (alunoExistente) {
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

            val matriculaPermitida =
                cursoPretendido.alunosMatriculados.stream()
                    .noneMatch { al -> al.codigo == codigoAluno }

            val matriculasAtuais = matriculas.stream()
                .filter { m -> m.aluno.codigo == codigoAluno }
                .filter { m -> m.curso.codigo == codigoCurso }
                .findFirst()
                .isPresent

            if (matriculaPermitida && !matriculasAtuais && cursoPretendido.adicionarUmAluno(alunoPretendido)) {
                matriculas.add(Matricula(alunoPretendido, cursoPretendido))
                cursoPretendido.alunosMatriculados.add(alunoPretendido)
                println("Matricula Realizada com sucesso no curso: ${cursoPretendido.nome} para o aluno: ${alunoPretendido.nome}")
            } else {
                println("Não há vagas disponíveis para o curso ${cursoPretendido.nome} para o aluno ${alunoPretendido.nome}")
            }

        } else {
            println("aluno não localizado")
        }

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

            val cursoMatricular = cursos.stream().filter { c -> c.codigo == codigoCurso }
                .findFirst()
                .get()

            if (cursoMatricular != null) {
                cursoMatricular.professorTitular = professorTitular as ProfessorTitular
                cursoMatricular.professorAdjunto = professorAdjunto as ProfessorAdjunto
                println("Professor Matriculado com Sucesso:" +
                        "\n ProfessorTitular: ${professorTitular.nome} " +
                        "\n ProfessorAdjunto: ${professorAdjunto.nome} \nCurso: ${cursoMatricular.nome}")

            }
        }
    }
}
