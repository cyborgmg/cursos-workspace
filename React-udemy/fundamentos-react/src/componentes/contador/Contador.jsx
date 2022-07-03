import React, { Component } from 'react'
import Botoes from './Botoes';
import './Contador.css'
import Display from './Display';
import PassoForm from './PassoForm';

export default class Contador extends Component{

    constructor(props){
        super(props);
        console.log( this.toString() )
    }

    state = {
        numero: this.props.numeroInicial || 1,
        passo: this.props.passo || 1
    };
    

    // constructor(props){

    //     super(props)

    //     this.state = {
    //         numero: props.numeroInicial
    //     }

    // }

    inc = () => {

        this.setState({
            numero: this.state.numero + this.state.passo
        })
    }

    dec = () => {

        this.setState({
            numero: this.state.numero - this.state.passo
        })
    }

    setPasso = (novoPasso) => {
        this.setState({
            passo: parseInt(novoPasso),
        })
    }


    render() {
        return (
            <div className="Contador">
                <h2>Contador</h2>
                <Display numero={this.state.numero}/>
                <PassoForm passo={this.state.passo} setPasso={this.setPasso} />
                <Botoes setInc={this.inc} setDec={this.dec} />
            </div>
        )
    }

}