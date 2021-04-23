import React, { useState } from 'react'
import IndiretaFilho from './IndiretaFilho'


export default props => {

    const [nome,setNome]  = useState('?');
    const [idade,setIdade] = useState(0);
    const [nerd,setNerd]  = useState(false);

    function fornecerInformacoes(pnome, pidade, pnerd){
        
        setNome(pnome);
        setIdade(pidade);
        setNerd(pnerd);
    }

    return (
        <div>
            <div>
                <span>{nome} </span>
                <br/>
                <span>{idade} </span>
                <br/>
                <span>{nerd?'Verdadeiro':'Falso'}!</span>
            </div>
            
            <IndiretaFilho quandoClicar={ fornecerInformacoes } />
        </div>
    )

}