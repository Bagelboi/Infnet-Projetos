export const GUNTYPES = {
    PISTOL:0,
    SMG:1,
    ASSAULT_RIFLE:2,
    SNIPER:3,
    SHOTGUN:4,
    EXPLOSIVES:5
}


export function getGuntypeStr(type) {
    switch (type) {
        case GUNTYPES.PISTOL:
            return "Pistola"
        case GUNTYPES.SMG:
            return "Fuzil de Mão"
        case GUNTYPES.ASSAULT_RIFLE:
            return "Fuzil de Assalto"
        case GUNTYPES.SNIPER:
            return "Escopeta"
        case GUNTYPES.SHOTGUN:
            return "Doze"
        case GUNTYPES.EXPLOSIVES:
            return "Bomba"   
        default:
            return "Invalido"     
    }
}

const gunsTab = "armas"

if (localStorage.getItem(gunsTab) == null) 
    localStorage.setItem("armas", JSON.stringify(Array(0)))

export function getGuns() {
    return JSON.parse(localStorage.getItem( gunsTab ))
}

export function saveGuns(obj) {
    localStorage.setItem( gunsTab, JSON.stringify(obj) )
    window.location.reload()
}

export function getGunByID(id) {
    return getGuns().findIndex( val => val.unique_id === id )
}

export function addGun(data) {
    const guns = getGuns()
    guns.push(data)
    saveGuns( guns )
}

export function updateGun(i, data) {
    const guns = getGuns()
    guns[i] = data
    saveGuns(guns)
}

export function deleteGunIndex(i) {
   const guns = getGuns()
   guns.splice(i, 1)
   saveGuns( guns )
}

export function sortClone(arr, func) {
    arr.sort(func)

    return arr
}

export function formGun() {
    return {
        unique_id: "glock_9mm",
        foto:"https://beartac-imgs.s3.sa-east-1.amazonaws.com/uploads/pistola-glock-g19x-coyote-9mm-19-1-1.jpg",
        name:"Glock 9mm",
        price:5000,
        fabrication_date: new Date(1982, 1).toISOString().split('T')[0],
        type: GUNTYPES.PISTOL,
        mag_cap:30,
        bullet:"9mm",
        details:"Uma arma secundaria bem versatil e eficiente."
    }
}

if (localStorage.getItem("shop") == null) 
    localStorage.setItem("shop", "{}")

export function loadShop() {
    return JSON.parse(localStorage.getItem( "shop" ))
}

export function saveShop(data) {
    localStorage.setItem( "shop", JSON.stringify(data) )
    alert("Carrinho salvo")
}