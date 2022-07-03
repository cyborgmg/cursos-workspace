import React from 'react'
import If, { Else } from './If'

export default props => {

    const usuario = props.usuario || {};

    return (
        <div>
            <If test={usuario && usuario.nome}>
                Seja bem vindo <strong>{usuario.nome}</strong>!
                <Else>
                    Seja bem vindo <strong>Amigão</strong>!
                </Else>
            </If>
            {/* 
            <If test={usuario && usuario.email}>
                Seja bem vindo <strong>{usuario.email}</strong>!
            </If>
            <If test={ Object.keys(usuario).length === 0 }>
                Seja bem vindo <strong>Amigão</strong>!
            </If>
             */}
        </div>
    )

}