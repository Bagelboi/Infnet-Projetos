<template>
  <div class="home">
    <table v-if="!$store.state.reloaded && $store.state.prisonerList.length > 0">
      <tr>
            <th>CPF</th>
            <th>Nome</th>
            <th>Sexo</th>
            <th>Encarceramento</th>
            <th>Idade</th>
            <th>Liberado</th>
            <th>Status</th>
            <th>Foto</th>
        </tr>
      <PrisonerListing v-for="(pris, i) in store.getters.prisonerList" :prisoner="pris" :key="i"/>
    </table>
    <h2 v-else>Todos liberados!</h2>
  </div>
</template>

<script>
// @ is an alias to /src
import PrisonerListing from '@/components/PrisonerListing.vue';
import {Prisoner} from '@/lib/badboysApi'
import { useStore } from 'vuex';

export default {
  name: 'HomeView',
  components: {
    PrisonerListing
  },

  watch: {
    "$store.state.prisonerList": {
      deep:false,
      handler() {
        this.$store.state.reloaded = true
        this.$store.state.reloaded = false
      }
    }
  },

  created() {
    this.$store.dispatch('PRISONER_GETALL')
  },

  data() {
    return {
      store: useStore()
    }
  },

  methods: {
    nig() {
      return Prisoner.template()
    }
  }
}
</script>


<style>
  .home {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .home table td,th {
    border:rgba(245, 245, 245, 0.863) solid 1px;
    padding: 5px;
  }
</style>