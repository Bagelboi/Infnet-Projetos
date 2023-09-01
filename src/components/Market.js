import React, { Component } from 'react'
import * as MarketApi from '../lib/marketAPI'

class GunCreator extends Component {
    constructor(props) {
        super(props)
        console.log(props)
        this.state = this.props.gunData
    }
    render() {
        return (
        <div>
        <form onSubmit={evt => {evt.preventDefault(); this.newArma()}}>
            Identificador:<input value={this.state.unique_id} onChange={e=>this.updateInputValue(e, "unique_id")} type="text"></input><br></br>
            Foto <input value={this.state.foto} onChange={e=>this.updateInputValue(e, "foto")} type="text"></input><br></br>
            
            Tipo <select value={this.state.type} onChange={ e => { console.log(e.target.value); this.setState({type:+e.target.value}) } }>{(() => {
                let guntypes = []
                for (const key in MarketApi.GUNTYPES) {
                    guntypes.push(  <option key={key} value={ MarketApi.GUNTYPES[key] }> {MarketApi.getGuntypeStr(MarketApi.GUNTYPES[key])}</option> )
                }
                return guntypes
            })()}</select><br></br>

            Nome <input value={this.state.name} onChange={e=>this.updateInputValue(e, "name")} type="text"></input><br></br>
            Preço <input value={this.state.price} onChange={e=>this.updateInputValue(e, "price")} type="number"></input><br></br>
            Fabricação <input value={this.state.fabrication_date} onChange={e=>this.updateInputValue(e, "fabrication_date")} type="date"></input><br></br>
            Cartucho <input value={this.state.mag_cap} onChange={e=>this.updateInputValue(e, "mag_cap")} type="number"></input><br></br>
            Calibre <input value={this.state.bullet} onChange={e=>this.updateInputValue(e, "bullet")} type="text"></input><br></br>
            Detalhes<br></br> <textarea cols="40" rows="20" value={this.state.details} onChange={e=>this.updateInputValue(e, "details")}></textarea><br></br>

            <button type="submit">Adicionar</button>
        </form>
        </div>
        )
    }
    
    updateInputValue(evt, key) {
        let val = evt.target.value;
        console.log(val, key)
            
        this.setState({
          [key]: val
        });
    }
    
    newArma() {
        // call with prop

        if (this.validateFields()) {
            const data = {
                unique_id: this.state.unique_id,
                foto:this.state.foto,
                name:this.state.name,
                price:+this.state.price,
                fabrication_date: this.state.fabrication_date,
                type: this.state.type,
                mag_cap:this.state.mag_cap,
                bullet:this.state.bullet,
                details:this.state.details
            }
            const gun_i = MarketApi.getGunByID(this.state.unique_id)
            if (gun_i !== -1) {
                if (window.confirm("Deseja atualizar arma ja existente?") === true) {
                    MarketApi.updateGun(gun_i, data)
                    alert("Arma atualizada com sucesso")
                }
            } else {
                MarketApi.addGun( data )
                alert("Arma adicionada com sucesso")
            }
        }
    }

    validateFields() {
        if (this.state.unique_id.length < 4) {
            alert("ID unico curto demais!")
        }
        else if (this.state.name.length < 2)
            alert("Nome curto demais!")
        else if (this.state.price < 0)
            alert("Não existe preço negativo (imagino)")
        else if (this.state.mag_cap < 1)
            alert("Um cartucho precisa carregar pelo menos 1 bala")
        else if (this.state.bullet.length < 1)
            alert("Um tipo de bala precisa ser definido")
        else {
            return true
        }
        return false
    }
}


class GunItem extends Component {
    constructor(props) {
        super(props)
        this.state={editing:false}
    }

    render() {
        return(
            <tbody>
            <tr>
                <td><img src={this.props.gun.foto} alt="foto da arma" height="96" width="96"></img></td>
                <td>{this.props.gun.name}</td>
                <td>{this.props.gun.price}R$</td>
                <td>{MarketApi.getGuntypeStr( this.props.gun.type )}</td>
                <td>{this.props.gun.mag_cap} balas</td>
                <td>{this.props.gun.bullet}</td>
                <td>{this.props.gun.fabrication_date.split('T')[0]}</td>
            </tr>
            <tr>
                {this.state.editing && <td colSpan="7"><GunCreator gunData={this.props.gun}/></td>}
            </tr> 
            <tr>
                <td colSpan="4">{this.props.gun.details}</td>
                <td><button type="button" onClick={ evt => this.props.addToShop() } >+</button></td>
                <td><button type="button" onClick={evt => this.editClick()}>Editar</button></td>
                <td><button type="button" onClick={ evt => this.removeIndex() }>Remover</button></td>
            </tr>
            <tr className="gunlist-spacing"></tr>
            </tbody>
        )
    }

    removeIndex() {
        const gun_i = MarketApi.getGunByID(this.props.gun.unique_id)
        if (gun_i !== -1) {
            if (window.confirm("Deseja apagar essa arma?") === true) {
                MarketApi.deleteGunIndex(gun_i)
                alert("Arma apagada com sucesso")
            }
        } else {
            alert("Arma não existe?")
        }
    }

    editClick() {
        this.setState( {editing:!this.state.editing} )
    }

}

class ShoppingList extends Component {
    constructor(props) {
        super(props)
        this.state={items:MarketApi.loadShop()}
    }

    render() {
        return(
            <div className="gunlist">
            <h2>Carrinho</h2>
            <table>
            <tr>
                <th></th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Total</th>
            </tr>
                { Object.keys(this.state.items).map( val => (  <tr>
                    <td><img src={this.state.items[val].foto} alt="foto da arma" height="96" width="96"></img></td> 
                    <td>{this.state.items[val].name}</td>
                    <td>{this.state.items[val].stock}</td>
                    <td>{this.state.items[val].price * this.state.items[val].stock}</td>
                    <button onClick={evt => {
                        const item=this.state.items 
                        if (item[val].stock < 2)
                            delete item[val]
                        else
                            item[val].stock -= 1 
                        this.setState( {items: {...item}} )
                        }}>-</button>
                    </tr>) ) } 
            <tr>
                <td>Total { Object.keys(this.state.items).reduce( (prev, cur) => prev + this.state.items[cur].price * this.state.items[cur].stock, 0 ) }</td>
                <td><button onClick={e=>MarketApi.saveShop(this.state.items)}>Salvar</button></td>
            </tr>
            </table>
            </div>
        )
    }

    addToList(gundata) {
        const item = this.state.items
        if (gundata !== null) {
            if (gundata.unique_id in item) 
                item[gundata.unique_id].stock += 1
            else
                item[gundata.unique_id] = {
                    foto:gundata.foto,
                    name:gundata.name,
                    stock:1,
                    price:gundata.price
                }
        }
        this.setState( {items: {...item}} )
    }
}

export class GunList extends Component {

    constructor(props) {
        super(props)
        this.state = {create:false}
        this.shop_ref = React.createRef()
        this.addToShop = this.addToShop.bind(this)
    }

    render() {
        return (<div className="gunlist">
            <button type="button" onClick={() => this.setState({create:!this.state.create})}>Abrir painel de criação</button>
            {this.state.create && <GunCreator gunData={MarketApi.formGun()}/>}
            

            <table>
                <tbody><tr>
                    <th></th>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Tipo</th>
                    <th>Cartucho</th>
                    <th>Calibre</th>
                    <th>Fabricação</th>
                </tr></tbody>
              { MarketApi.getGuns().length > 0 ?
               ( MarketApi.sortClone(MarketApi.getGuns(), (a,b) => a.type - b.type).map( (gun, i) => ( <GunItem key={gun.unique_id} addToShop={ (() => this.addToShop(gun)) } gun={gun}/> ) )) :
               ( <h2>Lista vazia!</h2> ) }
            </table>

            <ShoppingList ref={this.shop_ref}/>
        </div>)
    }

    addToShop(gun) {
        if (this.shop_ref.current != null)
            this.shop_ref.current.addToList(gun)
    }
}


export default GunList;