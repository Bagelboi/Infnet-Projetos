// badboys badboys, watchu gonna do, watchu gonna do when they come for u //
import { openDB} from 'idb';
import { convertDecimalDate } from './extras';

export class Prisoner {
    static GENDER = { MALE:0, FEMALE:1 };
    static PENA = { MURDER:0, MANSLAUGHTER:1, DRUGDEAL:2, CONTRABAND:3, FRAUD:4, ROBBERY:5 };
    static pena_data = {
        [Prisoner.PENA.MURDER] : {
            txt:"Assassinato",
            min_fine:10,
            max_fine:30
        },
        [Prisoner.PENA.MANSLAUGHTER] : {
            txt:"Homicidio Culposo",
            min_fine:5,
            max_fine:10
        },
        [Prisoner.PENA.DRUGDEAL] : {
            txt:"Trafico de Drogas",
            min_fine:2,
            max_fine:8
        },
        [Prisoner.PENA.CONTRABAND] : {
            txt:"Contrabando",
            min_fine:0.5,
            max_fine:5
        },
        [Prisoner.PENA.FRAUD] : {
            txt:"Fraude",
            min_fine:0.2,
            max_fine:2
        },
        [Prisoner.PENA.ROBBERY] : {
            txt:"Roubo",
            min_fine:2,
            max_fine:15
        },
    }

    static template() {
        return {
            name:"NAME",
            reg_date: Date.now(),
            gender:Prisoner.GENDER.MALE,
            mugshot:"https://raptv.com/wp-content/uploads/2019/05/F1501AF9-242C-419E-82FE-62FC40B1D5C9.jpeg",
            altura:180,
            peso: 90,
            age:18,
            notes:"",
            penas: [ [Prisoner.PENA.ROBBERY, 0.25] ]
        }
    }

    static calcPenaSingle(tipo, sever) {
        const fine_data = Prisoner.pena_data[ tipo ]
        console.log(fine_data)
        return fine_data.min_fine + ( fine_data.max_fine - fine_data.min_fine ) * sever
    }

    static calcPenas(penas) {
        let total = 0
        penas.forEach(pena => { 
            console.log(pena)
            total += Prisoner.calcPenaSingle(pena[0], pena[1])
        });
        return total
    }

    static getGender(gender) {
        if (gender == Prisoner.GENDER.MALE)
            return "Homem"
        return "Mulher"
    }

    static getReleaseDate(reg_date, penas) {
        reg_date = new Date(reg_date)
        return convertDecimalDate( reg_date.getFullYear() + Prisoner.calcPenas( penas ) )
    }
}

// db stuff

export const badDbStore = 'badboys'

export const badDbPromise = openDB( "Badboys DB", 1, {
    upgrade(db) {
        db.createObjectStore(badDbStore, {
            keyPath: 'id',
            autoIncrement: false,
        })
    }
} )

export async function badDb_get(key) {
    return (await badDbPromise).get(badDbStore, key)
}

export async function badDb_exists(key) {
    return (await badDbPromise).getKey(badDbStore, key)
}

export async function badDb_getAll() {
    return (await badDbPromise).getAll(badDbStore)
}

export async function badDb_getKeys() {
    return (await badDbPromise).getAllKeys( badDbStore )
}

export async function badDb_set(key, val) {
    console.log(key, val)
    val["id"] = key
    return (await badDbPromise).put(badDbStore, val)
}

export async function badDb_delete(key) {
    return (await badDbPromise).delete(badDbStore, key)
}

//badDb_set("11248094735", Prisoner.template())

export default {badDbPromise, badDbStore, Prisoner}