Projetos da Infnet
Aqui estão projetos destacados que foram feitos no Instituto Infnet por mim ou em grupo durante os trimestres/blocos ja decorridos.

# Bloco de Android
Esse foi meu primeiro bloco e consideravelmente o mais dificil comparado ao resto que cursei depois e que presentemente curso.
<details>
  <summary>Projeto do Bloco : Unhooked</summary>

  ## Link do APK
  https://play.google.com/store/apps/details?id=com.danielgomeslipkin.unhooked.app&pli=1
  
  ## O projeto
  O projeto do bloco consistiu em criar um aplicativo hosteado na Play Store com anuncios, com integração ao Firebase e organizado com a metodologia SCRUM. Esse trabalho, dado a complexidade, devia ser feito em grupo porem devido ao tamanho pequeno da turma e o fato de que minha dupla abandonou a Infnet no meio do processo, tornando ele muito mais arduoso que o esperado. A proposta do aplicativo é basicamente um rastreador de vicios pessoais, como cigarros, com o intuito de auxiliar o usuario para controlar ele.

  <b> NOTE QUE NÃO SOU PROFISSIONAL DE PSICANALISE OU PSICOTERAPIA E QUALQUER DADO IMPERATIVO PRESENTE NO PROJETO É SOMENTE POR RAZÕES DE TESTE E PROTOTIPO. </b>

  ## Funcionalidades Completas
  - Login com Firebase, incluindo a opção de utilizar o Facebook
  - Dados dos Vicios retirados do Firebase Realtime Storage com a biblioteca Retrofit.
  - Dados dos usuarios e os seus vicios armazenados no Firebase Firestore

## Fotos
 Notara que o design é muito arcaico e certas coisas parecem estar desalinhadas, displays como o Grafico linear não aparentam utilidade e certas funcionalidades como vicios customizados do usuario não foram   
 implementados. Talvez revisite no futuro para dar uma aprimorada.

 ### Pagina de Login
<img src="https://i.imgur.com/hmMUjHy.png" width="30%">

### Dashboard
<img src="https://i.imgur.com/nK9cykG.png" width="30%">

### Configuração do Usuario
<img src="https://i.imgur.com/34dScRX.png" width="30%"><br>
Resetar Tudo retira todos os vicios do usuario e os dados relativos

### Pagina dos Vicios
<img src="https://i.imgur.com/Y3eY8zJ.png" width="30%"><br>
Era pra ter uma logo para cada vicio. Ver somente permite o usuario ter uma noção doque se trata, Add(icionar) disponibiliza a integração á conta do usuario.

### Pagina de Customização de Vicio
<img src="https://i.imgur.com/A1ZTvIA.png" width="30%">
<img src="https://i.imgur.com/1KH2TZP.png" width="30%">

### Pagina de Visualização do Vicio
<img src="https://i.imgur.com/NVZXCfO.png" width="30%"><br>
Fatos circulam aleatoriamente, a frase em cima não.<br>

<img src="https://i.imgur.com/yQBq9K4.png" width="30%"><br>
Ao clica Adicionar Uso, voce seleciona uma data e adicione as horas usadas (TV por exemplo) ou unidades usadas (cigarros pro exemplo) naquela data. O aplicativo disponibilizara esses pontos de uso no Grafico e te dara uma Avaliação Geral de acordo com uma media arbitraria. Se por exemplo voce fumou 5 cigarros essa semana, sua avaliação sera "Boa".
</details>

<details>
  <summary>Outros</summary>

  ## Criador de Personagem RPG
  Esse foi um mini-projeto que visava aplicar nosso conhecimento de Intents e a transição entre Atividades/Paginas diferentes no Aplicativo. Eu tive a ideia de criar um criador de personagem para um RPG ficticio. O projeto original não salvava os personagens criados após fechar o aplicativo pela falta de experiencia, por isso adicionei rapidinho uma integração com o sistema de arquivos interno do Android para persistir os dados.

  O codigo esta na ramificação [android_mikes_mmo](https://github.com/Bagelboi/Infnet-Projetos/tree/android_mikes_mmo)

 ### Pagina de Criação
  <img src="https://i.imgur.com/Ot4t6a1.png" width="30%"><br>
  O botão Classe vai para a seleção de Classe, Logo abre uma intent de escolher imagem do dispositivo e mudar a logo em cima do formulario e Creditos vai para a pagina de creditos

  ### Pagina de Seleção de Classe
  <img src="https://i.imgur.com/OZzAmos.png" width="30%"><br>
  As habilidades são marcadas se o nivel determinado na criação iguala ou supera o nivel da habilidade

  ### Pagina de Personagens criados
  <img src="https://i.imgur.com/Kukp6Qn.png" width="30%">
  <img src="https://i.imgur.com/KPfvzMC.png" width="30%"><br>
  O (+) adiciona um personagem novo e a lixeira apaga
  
  ### Pagina de Creditos
  <img src="https://i.imgur.com/XmVVJeJ.png" width="30%">


  ## Aplicativo de Notas
  Esse foi o trabalho final de uma disciplina que visava testar nosso conhecimento de Keystores, Anuncios, Arquivos Criptografados, Autenticação Firebase e Permissões (Principalmente relacionadas á Localização do usuario).

  ### Pagina Pre-Login
  <img src="https://i.imgur.com/QXdEiB6.png" width="30%">
  
  ### Pagina de Login
  <img src="https://i.imgur.com/3em1C9P.png" width="30%">

  ### Pagina de Notas
  <img src="https://i.imgur.com/c1yWg0L.png" width="30%">
  <img src="https://i.imgur.com/acxWkSr.png" width="30%"><br>
  Ao clicar o sifrão, se estivesse integrado o aplicativo á Play Store, um pagamento seria requisitado para comprar a versão premium da secunda figura (Sem anuncios). Mas ao clicar, ja dispõe ela de graça.

  ### Pagina de Criação de Nota
  <img src="https://i.imgur.com/VPLLa7y.png" width="30%"><br>
  O logo de localização pede a permissão de local. Os outros dois logos são para selecionar ou tirar foto para a nota. Ela então é salva como um arquivo criptografado e somente acessivel se o usuario logado for o que criou a nota.
  
</details>

# Bloco de Web Full Stack
<details>
  <summary>4reads</summary>
  
  ## O projeto
  O projeto do bloco consistiu em criar um site full-stack que utilizava o Firebase para atuar como banco de dados e o Vue para gerenciar o MVC.
  A ideia foi um site no estilo de Goodreads, onde você seria recompensado pontos por criticar, comentar, etc sobre os livros e lojistas ofereceriam cupons para quem quisesse gastar esses pontos e ganhar promoções na compra de livros. 
  E por mais que não esta completo tudo tudo tudo, estou bem orgulhoso do resultado inicial. Devo credito pela minha dupla Lucas Dias de Gondra, um estudante de Engenharia de Software com muito potencial.

  ## Funcionalidades Completas
  - Login com Firebase
  - Dados armazenados no Firebase
  - Sistema de criticas e comentarios com likes/dislikes
  - Sistema de pontos e cupons
  - Dados dos Livros retirados da API do OpenLibrary

## Fotos
### Landing Page
<img src="https://i.imgur.com/nOgBtEK.png" width="56%"><br>
<img src="https://i.imgur.com/OeEmeQU.png" width="56%"><br>
### Busca
<img src="https://i.imgur.com/8SBcrfc.png" width="56%"><br>
### Pagina do Livro
<img src="https://i.imgur.com/bJ8kItt.png" width="56%"><br>
<img src="https://i.\imgur.com/bJ8kItt.png" width="56%"><br>
Com criticas<br>
<img src="https://i.imgur.com/JmksKXF.png" width="56%"><br>
### Pagina do Usuario
<img src="https://i.imgur.com/MpfGWJy.png" width="56%"><br>
<img src="https://i.imgur.com/09Zkz7r.png" width="56%"><br>
### Ranking dos Livros
<img src="https://i.imgur.com/6dHJJEc.png" width="56%"><br>
### Pagina de Cupons
O primeiro cupom foi usado pelo usuario logado<br>
<img src="https://i.imgur.com/Xh1F99w.png" width="56%"><br>
</details>
<details>
  <summary>Outros</summary>

  ## Jogo da Memória
  Trabalho final da disciplina de introdução á Javascript. Tem um placar para cada configuração de jogo e um foguinho que muda de rosto no topo dependendo da sua velocidade em acertar os pares.<br>

O codigo esta na ramificação [js_jogo_de_advinha](https://github.com/Bagelboi/Infnet-Projetos/tree/js_jogo_de_advinha)

  
  ### Estado Inicial
  <img src="https://i.imgur.com/5C1SOkr.png" width="56%"><br>
  ### Jogo em Processo
  4 cartas por imagem, 2 cartas por par, 8 cartas total<br>
  <img src="https://i.imgur.com/7n1vMRj.png" width="56%"><br>
  ### Scoreboard em baixo
  <img src="https://i.imgur.com/3i5kbMh.png" width="56%"><br>

## GK Fashion
O projeto final da disciplina de introdução á design de Front-End.<br>

O codigo esta na ramificação [css_loja_de_moda](https://github.com/Bagelboi/Infnet-Projetos/tree/css_loja_de_moda)
### Pagina Inicial
<img src="https://i.imgur.com/GLjxclt.png" width="56%"><br>
<img src="https://i.imgur.com/iGcWub1.png" width="56%"><br>
### Pagina Sobre
<img src="https://i.imgur.com/wKCvFGA.png" width="56%"><br>

  ## Universo do Zé
  O projeto final da disciplina de frameworks de Javascript foram 2 CRUDs basicos feitos em React e Vue. O feito em Vue foi um sistema de encarceramento e o feito em React foi uma loja de armas.<br>

  Os códigos estão nas ramificações<br>
  [js_reactjs_armas](https://github.com/Bagelboi/Infnet-Projetos/tree/js_reactjs_armas)<br>
  [js_vuejs_prisao](https://github.com/Bagelboi/Infnet-Projetos/tree/js_vuejs_prisao)
  
  ### Cadeia do Zé (Vue)
<img src="https://i.imgur.com/hh1n7v7.png" width="56%"><br>
<img src="https://i.imgur.com/N9kQS0D.png" width="56%"><br>
<img src="https://i.imgur.com/9RpPAq1.png" width="56%"><br>
  ### Armamentos do Zé (React)
  <img src="https://i.imgur.com/hh6oLel.png" width="56%"><br>
  <img src="https://i.imgur.com/hjJfQ7t.png" width="56%"><br>
  <img src="https://i.imgur.com/AGzY6OF.png" width="56%"><br>
  </details>

</details>
