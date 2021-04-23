import React from 'react'
import Card from './Card'

// eslint-disable-next-line import/no-anonymous-default-export
export default props => {

    return (
        <Card title="Sorteio de um Numero" purple>
            <div className="Intervalo">
                <span>
                    <span>Resultado: </span>
                    <strong>8</strong>
                </span>
            </div>
        </Card>
    )

}