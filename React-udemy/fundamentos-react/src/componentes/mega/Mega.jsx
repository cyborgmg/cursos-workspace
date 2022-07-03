import React, { useState } from 'react'
import './Mega.css'


export default props => {

    function geraNumeros(qtd) {
    
        const numeros = Array(qtd)
            .fill(0)
            .reduce((nums)=>{
                const novoNumero = gerarNumeroNaoContido(1, 60, nums)
                return [...nums , novoNumero ]
            },[])
            .sort((n1,n2)=> n1-n2)
    
        return numeros
    
    }
    
    
    function gerarNumeroNaoContido(min, max, ...array ) {
    
        const aleatorio = parseInt(Math.random()*(max+1-min)-min)
    
        return array.includes(aleatorio) ? gerarNumeroNaoContido(min, max, array ) : aleatorio
        
    }

    const [qtde,setQtde] = useState(props.qtde || 6)
    const numerosIniciais = Array(qtde).fill(0);
    const [numeros,setNumeros] = useState(numerosIniciais)

    return (
        <div className="Mega">
            <h2>Mega</h2>
            <div>
                <label>Qtd Numeros</label>
                <input type="number" value={qtde} 
                min={6}
                max={17}
                onChange={(e) => {
                    setQtde( +e.target.value )
                    setNumeros(geraNumeros( +e.target.value ))
                    }}
                />
            </div>
            <h3>{numeros.join(' ')}</h3>
            <button onClick={_=>setNumeros(geraNumeros(qtde))}>Gegar Numeros</button>
        </div>
    )

}