import { createStore } from 'vuex'
import * as Badboys from '@/lib/badboysApi'

export default createStore({
  state: {
    prisonerList:Array(0),
    reloaded:true
  },
  getters: {
    prisonerList(state) {
      return state.prisonerList.sort( (a, b) => b.reg_date - a.reg_date )
    },

    prisonerTotal(state) {
      return state.prisonerList.length
    }
  },
  mutations: {
    prisoner_addlist(state, data) {
      console.log(data)
      state.prisonerList = data
      console.log(state.prisonerList)
    },

    prisoner_set(state, data) {
      const i = state.prisonerList.findIndex( pr => pr.id == data.cpf )
      data.prisoner["id"] = data.cpf
      console.log(i)
      if (i != undefined)
        state.prisonerList[i] = data.prisoner
    },

    prisoner_delete(state, id) {
      const i = state.prisonerList.findIndex( pr => pr.id == id )
      console.log(i)
      if (i != undefined) 
        state.prisonerList.splice(i, 1)
      else
        state.dispatch('PRISONER_GETALL')
    }
  },
  actions: {
    async PRISONER_GETALL(state) {
      try {
        state.reloaded = true
        state.commit('prisoner_addlist', await Badboys.badDb_getAll())
      } catch (e) {
        alert("Nao foi possivel pegar os dados da base de dados")
      }
    },

    async PRISONER_SET(state, data) {
      try {
        await Badboys.badDb_set(data.cpf, data.prisoner)
        if (data.update) {
          alert("Atualizado com sucesso")
          state.commit('prisoner_set', data)
        }
        else {
          alert("Cadastrado com sucesso")
        }
      } catch (error) {
        console.log(error)
        alert("Nao foi possivel criar os dados na base de dados")
      }
    },

    async PRISONER_DELETE(state, id) {
      try {
        await Badboys.badDb_delete(id)
        alert("Apagado com sucesso")
        state.commit('prisoner_delete', id)
      } catch (error) {
        alert("Nao foi possivel deletar")
      }
    }
  },
  modules: {
  }
})
