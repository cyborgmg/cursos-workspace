import React from 'react';
import Primeiro from './Primeiro';
import ComParametro from './ComParametro';

export default props => {

    return (
        <React.Fragment>
            <Primeiro/>
            <ComParametro titulo="status do aluno" aluno="pedro" nota={props.nota} />
        </React.Fragment>
    )

}