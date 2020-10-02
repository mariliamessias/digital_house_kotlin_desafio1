package br.com.digital_house.desafio

fun main(){

    val gerenciador = DigitalHouseManager()

    gerenciador.registrarProfessorAdjunto("Professor Adjunto 1", "Sobrenome Professor Adjunto 1", 1, 1)
    gerenciador.registrarProfessorAdjunto("Professor Adjunto 2", "Sobrenome Professor Adjunto 2", 2, 1)

    gerenciador.registrarProfessorTitular("Professor Titular 1", "Sobrenome Professor Titular 1", 3, "matematica")
    gerenciador.registrarProfessorTitular("Professor Titular 2", "Sobrenome Professor Titular 2", 4, "programacao")

    gerenciador.registrarCurso("Full Stack", 20001, 3)
    gerenciador.registrarCurso("Android", 20002, 2)

    gerenciador.alocarProfessores(20001, 3, 1)
    gerenciador.alocarProfessores(20002, 4, 2)

    gerenciador.matricularAluno(1, 20002 )

    gerenciador.matricularAluno("Aluno 1", "Sobrenome Aluno 1", 1)
    gerenciador.matricularAluno("Aluno 2", "Sobrenome Aluno 2", 2)

    gerenciador.matricularAluno(1, 20001 )
    gerenciador.matricularAluno(2, 20001 )
    gerenciador.matricularAluno(3, 20001 )


    gerenciador.matricularAluno("Aluno 3", "Sobrenome Aluno 3", 3)
    gerenciador.matricularAluno("Aluno 4", "Sobrenome Aluno 4", 4)
    gerenciador.matricularAluno("Aluno 5", "Sobrenome Aluno 5", 5)

    gerenciador.matricularAluno(3, 20002 )
    gerenciador.matricularAluno(4, 20002 )
    gerenciador.matricularAluno(5, 20002 )
}