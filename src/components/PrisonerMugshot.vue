<template>
    <div class="mugshot" :style="getStyle()"> 
        <img class="mugshot" :src="source" :width="+wi" :height="+he">
        <img src="../assets/mugshot.png" :width="+wi" :height="+he">
    </div>
</template>

<script>
export default {
    props: {
        source:String,
        wi:Number,
        he:Number
    },

    methods: {
        getStyle() {
            return {
                right: this.wi/2 + "px",
                width: this.wi + "px",
                height: this.he + "px"
            }
        }
    }
}
</script>

<style>
    .mugshot {
        position: relative;
    }
    .mugshot img {
        position: absolute;
    }
</style>