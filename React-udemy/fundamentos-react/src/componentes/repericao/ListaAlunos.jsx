import React from 'react'
import alunos from '../../data/alunos'


export default props => {

    const alunoMaps = alunos.map(aluno => {
        return (
            <li key={aluno.id} >
                {aluno.id}) {aluno.nome} {"->"} {aluno.nota}
            </li>
        )
    })

    return (
        <div>
            <ul style={{listStyle: "none"}}>
                {alunoMaps}
            </ul>
        </div>
    )

}
