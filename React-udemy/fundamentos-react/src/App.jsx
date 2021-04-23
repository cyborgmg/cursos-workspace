import React from 'react'
import Fragmento from './componentes/basicos/Fragmento';
import Aleatorio from './componentes/basicos/Aleatorio';
import Card from './componentes/layout/Card';
import './App.css'
import Familia from './componentes/basicos/Familia'
import FamiliaMembro from './componentes/basicos/FamiliaMembro'
import ListaAlunos from './componentes/repericao/ListaAlunos';
import TabelaProdutos from './componentes/repericao/TabelaProdutos';
import ParOuImpar from './componentes/condicional/ParOuImpar';
import UsuarioInf from './componentes/condicional/UsuarioInf';
import DiretaPai from './componentes/comunicacao/DiretaPai';
import IndiretaPai from './componentes/comunicacao/IndiretaPai';
import Input from './componentes/formulario/Input';
import Contador from './componentes/contador/Contador';
import Mega from './componentes/mega/Mega';

export default props =>   
                    <div className="App">
                        <h1>Fundamentos React</h1>
                        <div className="Cards">

                            <Card titulo="Mega Sena" color="#1576C0">
                                <Mega qtde={7} />
                            </Card>

                            <Card titulo="Contador" color="#1576C0">
                                <Contador numeroInicial={10} />
                            </Card>

                            <Card titulo="Componente Controlado" color="#1576C0">
                                <Input />
                            </Card>

                            <Card titulo="Comunicação indireta" color="#1576C0">
                                <IndiretaPai />
                            </Card>

                            <Card titulo="Comunicação direta" color="#1576C0">
                                <DiretaPai />
                            </Card>

                            <Card titulo="Renderização condicional" color="#1576C0">
                                <UsuarioInf usuario={{ nome: 'Fernando'}}/>
                                <UsuarioInf />
                            </Card>

                            <Card titulo="Renderização condicional" color="#1576C0">
                                <ParOuImpar numero={20}/>
                            </Card>

                            <Card titulo="Repetição Tabela" color="#1576C0">
                                <TabelaProdutos />
                            </Card>

                            <Card titulo="Repetição Alunos" color="#1576C0">
                                <ListaAlunos/>
                            </Card>

                            <Card titulo="Familia" color="#1565C0">
                                <Familia sobrenome="Ferreira" >

                                    <FamiliaMembro nome="Pedro" />
                                    <FamiliaMembro nome="Maria" />
                                    <FamiliaMembro nome="Joana" /> 

                                </Familia>
                            </Card>

                            <Card titulo="exemplo de card" color="#00897B">
                                <Aleatorio min={1} max={60} />
                            </Card>

                            <Card titulo="Fragmento" color="#FFA000">
                                <Fragmento nota={props.nota}/>
                            </Card>

                        </div>
                    </div>
    