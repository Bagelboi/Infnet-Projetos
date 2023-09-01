app["options"] = {

    customImgs : [],

    loadImgs : function() {
        //carrega imagens customizadas

        const files = document.getElementById("option_add_img").files

        if (!files || files.length < 1) 
            return

        let imgs = []

        for (let i = 0; i < files.length; i++) {
            const fr = new FileReader
            fr.onload = (ev) => this.customImgs.push(ev.target.result)
            fr.readAsDataURL(files[i])
        }

    },

    getCardDupe : function() {
        // carrega cartas por imagem definidas pelo usuario
        let dupe = $("#option_card_dupe").val()

        if (dupe.length < 1)
            dupe = 2
        
        return dupe
    },

    getCardMax : function() {
        // carrega cartas por par definidas pelo usuario
        let max = $("#option_card_max").val()

        if (max.length < 1)
            max = 2
        
        return max
    }

}