<template>
  <div class="creator">
    <form @submit.prevent="postPrisoner">
      <PrisonerMugshot :source="editData.mugshot" he="128" wi="128"/>
      <label>URL da Foto: <input type="text" v-model="editData.mugshot"></label>
      <label>CPF: <input type="text" :value="editIndex" @input="editIndex = getCpf($event.target.value)"></label>
      <label>Nome: <input type="text" v-model="editData.name"></label>
      <select v-model="editData.gender">
        <option disabled value="">Sexo (Selecione)</option>
        <option value="0" type="number">Homem</option>
        <option value="1" type="number">Mulher</option>
      </select>
      <label>Idade: <input min="18" type="number" v-model="editData.age"></label>
      <label>Encarceramento: <input type="date" 
        @input="dateHandler($event.target.value)" :value="new Date(editData.reg_date).toISOString().slice(0, 10)">
      </label>
      <label>Altura: <input min="1" type="number" v-model="editData.altura"></label>
      <label>Peso: <input min="1" type="number" v-model="editData.peso"></label>
      <label>Considerações:</label>
      <textarea rows="8" cols="50" v-model="editData.notes"></textarea>

      <h2>Penas</h2>
      <select v-model="penaEdit.tipo">
        <option disabled value="">Crime (Selecione)</option>
        <option v-for="crime in getCrimesSelect()" 
          :key="crime"
          :value="crime" 
          type="number">{{ getCrimeData(crime).txt }} ({{ getCrimeData(crime).min_fine }} - {{ getCrimeData(crime).max_fine }} anos)
        </option>
      </select>
      <label><input type="number" min=0 max=100 v-model="penaEdit.severidade">% da pena maxima ({{ getPenaEditTime }} anos)</label>
      <button @click="addPena" type="button">Adicionar pena</button>

      <CrimeTable :edit="true" :_penas="editData.penas" ref="crimTab"/>

      <button type="submit">Terminar</button>
    </form>
  </div>
</template>

<script>
import { badDb_get, badDb_exists, Prisoner } from '@/lib/badboysApi';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import PrisonerMugshot from '@/components/PrisonerMugshot.vue';
import { cpf, cloneDeep } from '@/lib/extras';
import CrimeTable from '@/components/CrimeTable.vue';

export default {
  components: {
    PrisonerMugshot,
    CrimeTable
  },
  data() {
    return {
      store:useStore(),
      route:useRoute(),
      editData: {
        name:"",
        gender:"",
        reg_date:0,
        mugshot:"",
        notes:"",
        altura:0,
        peso: 0,
        age: 18,
        penas:Array(0)
      },
      editIndex:"",
      penaEdit: {
        tipo:"",
        severidade:1
      }
    }
  },
  
  async mounted() {
    if( this.route.params.dbkey != "*" ) {
      try {
        const eindex = this.route.params.dbkey
        Object.assign(this.editData, await badDb_get(eindex))
        this.editIndex = this.getCpf(eindex)
        this.$refs.crimTab.penas = this.editData.penas
        console.log(this.editData)
      } catch (error) {
        alert("nao pego")
      }
    }
  },

  computed: {
    getPenaEditTime() {
      if (this.penaEdit.tipo !== "")
        return Prisoner.calcPenaSingle(this.penaEdit.tipo, this.penaEdit.severidade / 100) 
      return "?"
    }
  },

  methods: {
    dateHandler(dateVal) {
      const now = new Date()
      this.editData.reg_date = new Date(dateVal).getTime() + now.getHours() + now.getMinutes() + now.getSeconds()
      console.log(this.editData.reg_date)
    },

    getCrimesSelect() {
      return Prisoner.PENA
    },

    isCamposValid() {
      this.editData.penas = this.$refs.crimTab.penas

      if (this.editIndex.length != 14) 
        alert("Formatação do CPF invalida")
      else if (this.editData.name.length < 2)
        alert("Nome precisa ser maior que 2 caracteres")
      else if (this.editData.gender === "")
        alert("Selecione um sexo")
      else if (this.editData.reg_date == 0)
        alert("Selecione uma data")
      else if (this.editData.altura < 1)
        alert("Altura invalida")
      else if (this.editData.peso < 1)
        alert("Peso invalido")
      else if (this.editData.age < 18)
        alert("Só adulto aqui")
      else if (this.editData.penas.length < 1)
        alert("Prendemos criminosos, não inocentes")
      else  
        return true
      return false
    },

    addPena() {
    if ( this.penaEdit.tipo === "" )
      alert("Crime selecionado não existe")
    else if (this.penaEdit.severidade > 100 || this.penaEdit.severidade < 0)
      alert("Porcentagem de pena invalida (0-100%)")
    else {
      this.$refs.crimTab.penas.push([this.penaEdit.tipo, this.penaEdit.severidade/100])
      this.editData.penas = this.$refs.crimTab.penas
    }
    },

    async postPrisoner() {
      if (this.isCamposValid()) {
        const eCpf = this.editIndex
        let prisoner = cloneDeep( this.editData )
        if (await badDb_exists(eCpf)) {
          const doUpdate = confirm("Esse CPF ja existe, deseja sobrepor seus dados?")
          if (doUpdate == true)
            this.store.dispatch('PRISONER_SET', { cpf:eCpf, prisoner:prisoner, update:true })
        } else
            this.store.dispatch('PRISONER_SET', { cpf:eCpf, prisoner:prisoner, update:false })
      }
    },

    getCrimeData(pena) {
      return Prisoner.pena_data[pena]
    },


    getCpf(cp) {
      return cpf(cp)
    }
  },
}
</script>



<style>

.creator {
  font-size: 1.25em;
}

.creator form {
  display:flex;
  flex-direction: column;
  align-items: center;
}

.creator input, textarea {
  font-size: 0.95em;
}

.creator label {
  margin-top: 10px;
  margin-bottom: 10px;
}

</style>