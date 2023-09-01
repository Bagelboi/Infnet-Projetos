import React, { Component } from 'react'
import Logo from '../monkey.png'

class Declarations extends Component {
    render() {
        return (
            <div className="depor">
                <b>{this.props.name}</b>
                <i>"{this.props.text}"</i>
            </div>
        )
    }
}

export class SiteHeader extends Component {
    render() {
        return (
        <div>
            <h1>Armamentos do Zé</h1>
            <img src={Logo} alt="macaco fumante" height="150"></img> 
            <h2>Veja as declarações de nossos clientes!</h2>
            <Declarations name="Zé Armengo" text="Só coisa top, recomendo!"></Declarations>
            <h2>Catalogo de Armas</h2>
        </div>
        )
    }
}

export default SiteHeader