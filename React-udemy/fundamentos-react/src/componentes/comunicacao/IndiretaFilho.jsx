import React from 'react'


export default props => {

    const geraIdade = () => parseInt( Math.random() * (70 - 50) + 50 )
    const geraNerd = () => (parseInt( Math.random() * (70 - 50) + 50 ) % 2 - 0)

    return (
        <div>
            <label>Filho</label>
            <br/>
            <button onClick={_ => props.quandoClicar('Pai',geraIdade(),geraNerd())}>Fornecer informações</button>
        </div>
    )

}