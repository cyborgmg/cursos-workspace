import React from 'react'


export default props => {

    return (
        <div style={{
                    position: "relative",
                    paddingRight: "10px"
                }}>
                    <label htmlFor="passoInput">Passo</label>
                    <input id="passoInput" 
                    type="number"
                    value={props.passo}
                    onChange={ e => props.setPasso(parseInt(e.target.value)) }
                    />
        </div>
    )

}