
import React from 'react';

// eslint-disable-next-line import/no-anonymous-default-export
export default props => {

    const status = props.nota >= 7 ? 'Aprovado' : 'Reprovado'

    return( <div>
                <h2>{props.titulo}</h2>
                <h3>{props.aluno} tem nota {props.nota} esta {status}</h3>
            </div>
        )
}