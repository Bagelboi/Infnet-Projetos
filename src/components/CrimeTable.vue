<template>
<div v-if="this.penas != null">
    <table>
        <tr>
          <th>Crime</th>
          <th>Tempo</th>
        </tr>
        <tr v-for="(pena, i) in penas" :key="i">
          <td>{{ getCrimeData(pena[0]).txt }}</td>
          <td>{{ getPenaTime(pena[0], pena[1]) }} anos</td>
          <button v-if="edit" type="button" @click="penas.splice(i, 1)">Remover</button>
        </tr>
        <tr>
          <th colspan="3"> {{ getPenaTotal }} anos</th>
        </tr>
    </table>
</div>
</template>

<script>
import { Prisoner } from '@/lib/badboysApi';

export default {
    data() {
        return {
            penas: this._penas
        }
    },

    props : {
        _penas:Array,
        edit:Boolean
    },
    computed: {
        getPenaTotal() {
        return Prisoner.calcPenas(this.penas)
        }
    },

  methods: {
    getCrimeData(pena) {
      return Prisoner.pena_data[pena]
    },

    getPenaTime(pena, severidade) {
      if(pena !== "")
        return Prisoner.calcPenaSingle(pena, severidade)
      return -1
    },
  }
}
</script>