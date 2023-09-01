<template>
        <tr>
            <td>{{ prisoner.id }}</td>
            <td>{{ prisoner.name }}</td>
            <td>{{ getGender }}</td>
            <td>{{ getDate }}</td>
            <td>{{ prisoner.age }} anos</td>
            <td>{{ calcPena.toDateString() }}</td>
            <td>{{ getStatus }}</td>
            <td @click="showDetails"><PrisonerMugshot :source="prisoner.mugshot" :he="96" :wi="96"/></td>
        </tr>

        <tr v-if="details">
            <td colspan="10" class="ohhimark">
                <b>Altura:</b> {{ prisoner.altura }}cm<br>
                <b>Peso:</b> {{ prisoner.peso }} kg
                <hr>
                <CrimeTable :edit="false" :_penas="prisoner.penas" />
                <hr>
                <b>Considerações:</b> {{ prisoner.notes }}
                <hr>
                <router-link :to="`/office/${prisoner.id}`"><button>Editar</button></router-link>
                <button @click="deletePrisoner(prisoner.id)">Apagar</button>
            </td>
        </tr>
</template>

<script>

import PrisonerMugshot from "@/components/PrisonerMugshot.vue"
import {Prisoner} from "@/lib/badboysApi"
import CrimeTable from "./CrimeTable.vue"

export default {
    data() {
        return {
            details: false
        }
    },

    props: {
        prisoner: Object
    },

    components: {
    PrisonerMugshot,
    CrimeTable
},

    computed: {
        getGender() {
            return Prisoner.getGender( this.prisoner.gender )
        },
        
        getDate() {
            return new Date(this.prisoner.reg_date).toDateString()
        },
        
        calcPena() {
            //return Prisoner.calcPenas( this.prisoner.penas )
            return Prisoner.getReleaseDate(this.prisoner.reg_date, this.prisoner.penas)
        },

        getStatus() {
            return Date.now() >= this.calcPena ? "Liberado" : "Preso" 
        }
    },

    methods: {
        showDetails() {
            this.details = !this.details
        },

        async deletePrisoner(id) {
            this.$store.dispatch('PRISONER_DELETE', id)
        }
    }

}

</script>

<style>

.ohhimark {
    display:flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 25px;
}

.ohhimark hr {
    width:100%;
}


</style>