<template>
<div class="info">
    <figure>
        <img src="../assets/what.jpg">
        <figcaption>Nossa instalação em Nova Iguaçu</figcaption>
    </figure>
    <section>
        <h2>Quem somos?</h2>
        <hr>
        <p>Fundados em 1910, hoje em dia estamos armazenando {{ getPrisoners }} detentos de tipos e cores diferentes. Para protegemos a sociedade deles e vice-versa, sempre seguimos a mesma filosofia:</p>
        <i>"Respeite os seus superiores"</i>
        <p>Mas muitos de nossos convidados não respeitam essa regra e então precisam ser disciplinados com equipamentos neutralizadores, fornecidos graças aos Armamentos do Zé™.</p>
        <p>Assim podem voltar a sociedade após suas sentenças terminarem e prover um futuro melhor á um páis decadente</p>
        <br>
        <h2>Believe in yourself</h2>
        <hr>
    </section>
    <img src="../assets/show.jpg" height="500">

</div>
</template>

<script>
export default {
    computed: {
        getPrisoners() {
            return this.$store.getters.prisonerTotal
        }
    }
}
</script>

<style>

.info {
    display:flex;
    flex-direction: column;
    align-items: center;
}

.info section{
    text-align: left;
    width:75vw
}

.info hr {
    margin-bottom: 10px;
}

figure {
    background-color:rgb(30, 30, 53);
    padding: 30px;
    border-radius: 10px;
}

figcaption {
    margin-top: 10px;
}

</style>