app["game"] = {
    cartasMax : 2,
    cartasFlipped : [], 
    canSelectCards: false,
    gameBoard : $(".game_board"),
    gameTimer: $(".game_timer"),
    gameTime: 0,
    gameTimeInt: 0,
    solvedPairs : 0,
    
    arrayRandomRemove : function(arr) {
        // randomiza qualquer array, funcao de utilidade
        const newArr = []

        while (arr.length > 0) {
            const i = Math.floor(Math.random() * (arr.length))
            newArr.push(arr[i])
            arr.splice(i, 1)
        }

        return newArr
    },

    criarCarta : function(board, img_front, img_back) {
        // cria carta no html e adiciona ao board como filho

        const card = $(`
        <div class="card">
            <img class="back" src="${img_back}"> 
            <img class="front" src="${img_front}" style="display:none;"> 
        </div>`)

        card.click( () => this.cardClick(card) )

        card.appendTo(board)

        return card
    },

    gerarBoard : function(img_front=["img/chrome.png", "img/firefox.png"], 
                            img_back="backside.webp",
                            card_dupe = app.options.getCardDupe(), 
                            card_max = app.options.getCardMax()) 
        {
        /*
            img_front -> array de imagens
            img_back -> imagem da carta oculta
            card_dupe -> cartas por imagem
            card_max -> cartas por par
        */
        // remove imagem duplicada
        let arr_front = {}
        img_front.forEach( (val) => arr_front[val] = true )
        arr_front = Object.keys(arr_front)

        //carrega pontuacao
        app["extra"].recordHTML(card_dupe, card_max, arr_front.length)

        //randomiza cartas
        let cartasRandom = []

        if ( card_dupe % card_max != 0 )
            card_dupe = card_max*2

        for (let img_i = 0; img_i < arr_front.length; img_i++) {
            for (let i = 0; i < card_dupe; i++) {
                cartasRandom.push(img_i)
            }
        }

        for (const index of this.arrayRandomRemove(cartasRandom)) {
            this.cartasFlipped.push(this.criarCarta(this.gameBoard, img_back, arr_front[index]))
        }

        //setar variaveis inicias do jogo
        this.solvedPairs = card_dupe / card_max * arr_front.length
        this.cartasMax = card_max
        this.gameTime = 0 
        app["extra"].record2Set = [card_dupe, card_max, arr_front.length].join("|") 
        app["extra"].myManFeeling = 0.5
        
        //muda sprite do foguinho e aumenta tempo
        setTimeout(() => {this.unflipCartas()
            clearInterval(this.gameTimeInt) 
            this.gameTimeInt = setInterval(() => {this.gameTime += 1; 
                this.doTimer(this.gameTime);
                app["extra"].changeFlamieSprite(-0.05);
            }, 1000)}, 3000)
    },


    unflipCartas : function(arrCartas=this.cartasFlipped) {
        //desvira e cartas e torna elas selecionaveis
        for (const card of arrCartas) {
            card.data("selected", false)
            card.find(".back").slideUp(400, () => card.find(".front").slideDown())
        }

        this.cartasFlipped = []
        this.canSelectCards = true
    },


    verificarCartas : function(img, arrCartas=this.cartasFlipped) {
        //itera cada carta em cartasFlipped e verifica elas
        for (const card of arrCartas) {
            if (card.find(".back").attr("src") != img)
                return false
        }

        return true
    },

    cardVerificar : function(card) {
        //verifica carta
        const back = card.find(".back")
        const front = card.find(".front")

        if (back.is(":animated")) 
            return

        card.find(".front").slideUp(400, () => card.find(".back").slideDown())

        if ( this.verificarCartas(back.attr("src")) ) { 
            this.cartasFlipped.push(card)
            card.data("selected", true)

            if (this.cartasFlipped.length >= this.cartasMax) {
                // cartas viradas excedem limite de cartas por par, quer dizer que o usuario acertou
                this.cartasFlipped = []
                this.solvedPairs -= 1
                app["extra"].changeFlamieSprite(1)
                this.checkWin()
            }

        } 
        
        else {
            // usuario falhou
            this.canSelectCards = false
            this.cartasFlipped.push(card)
            setTimeout(() => this.unflipCartas(), 1500)
            app["extra"].changeFlamieSprite(-1)
        }
    },

    cardClick : function(card) {
        if (this.canSelectCards && !card.data("selected")) 
            this.cardVerificar(card)
    },

    checkWin : function() {
        //usuario venceu o jogo
        if (this.solvedPairs < 1) {
            clearInterval(this.gameTimeInt)    
            const name = prompt("Parabens! Digite um nome se quiser gravar seu tempo:")
            if (name != null && name.length > 0)
                app["extra"].recordSave(app["extra"].record2Set, this.gameTime, name)
            app["extra"].recordString(app["extra"].record2Set)
            
            //foguinho faz um passinho se ele ta feliz
            if (app["extra"].myManFeeling > 2) {
                let funnidance_flip = 1
                let funnidance_i = 0
                const funnidance = setInterval(() => {app["extra"].flamieDiv.css("transform", `scaleX(${funnidance_flip})`); 
                    funnidance_flip *= -1
                    funnidance_i += 1
                    if (funnidance_i > 10)
                        clearInterval(funnidance)}, 500)
            }
        }
    },

    doTimer : function(time) { //em segundos

        const min = Math.floor( time / 60 )
        const sec = Math.floor( time / (min+1) )
        const options = {minimumIntegerDigits: 2}

        this.gameTimer.text( min.toLocaleString( undefined, options ) + ":" + sec.toLocaleString( undefined, options ))
    },

    gameStart : function(imgs="") { 
        //funcao para inciar um jogo novo
        this.gameBoard.empty()

        const c_imgs = app.options.customImgs
        
        //imagens importadas
        if (c_imgs.length > 0) {
            imgs = c_imgs
        }
        //imagens default
        else if (imgs == "") {
            this.gerarBoard()
            return;
        }

        this.gerarBoard(imgs)
    }
}