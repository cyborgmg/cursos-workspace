import React from 'react'
import Card from './Card'

// eslint-disable-next-line import/no-anonymous-default-export
export default props => {

    return (
        <Card title="Média dos Numeros" green>
            <div className="Intervalo">
                <span>
                    <span>Resultado: </span>
                    <strong>5</strong>
                </span>
            </div>
        </Card>
    )

}