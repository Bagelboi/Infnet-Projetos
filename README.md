Projetos da Infnet
Aqui estão projetos destacados que foram feitos no Instituto Infnet por mim ou em grupo durante os trimestres/blocos ja decorridos.

# Bloco de Android
Esse foi meu primeiro bloco e consideravelmente o mais dificil comparado ao resto que cursei depois e que presentemente curso.
<details>
  <summary>Projeto do Bloco : Unhooked</summary>

  ## Link do APK
  https://play.google.com/store/apps/details?id=com.danielgomeslipkin.unhooked.app&pli=1
  
  ## O projeto
  O projeto do bloco consistiu em criar um aplicativo hosteado na Play Store com anuncios, com integração ao Firebase e organizado com a metodologia SCRUM. Esse trabalho devia ser feito em <b>GRUPO</b>. A proposta do aplicativo é basicamente um rastreador de vicios pessoais, como cigarros, com o intuito de auxiliar o usuario para controlar ele.

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

## Detalhes Técnicos
### SCRUM
<img src="https://i.imgur.com/Xgdmy6e.png" width="90%">

### Realtime Database
<img src="https://i.imgur.com/Fou6q6V.png" width="90%">

### Firestore
<img src="https://i.imgur.com/QyIqKd0.png" width="90%">
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
  ## Link do APK
  https://play.google.com/store/apps/details?id=com.danielgomeslipkin.unhooked.app&pli=1
  
  ## O projeto
  O projeto do bloco consistiu em criar um site full-stack que utilizava o Firebase para atuar como banco de dados e o Vue para gerenciar o MVC.
  A ideia foi um site no estilo de Goodreads, onde você seria recompensado pontos por criticar, comentar, etc sobre os livros e lojistas ofereceriam cupons para quem quisesse gastar esses pontos e ganhar promoções na compra de livros. 
  E por mais que não esta completo tudo tudo tudo, estou bem orgulhoso do resultado inicial. Devo credito pela minha dupla Lucas Dias de Gondra, um estudante de Engenharia de Software com muito potencial.

  ## Funcionalidades Completas
  - Login com Firebase
  - Dados armazenados no Firebase
  - Sistema de criticas e comentarios com likes/dislikes
  - Dados dos Livros retirados da API do OpenLibrary

## Fotos
###Landing Page
  <img src="https://i.imgur.com/nOgBtEK.png" width="30%"><br>
  <img src="https://i.imgur.com/OeEmeQU.png" width="30%"><br>
###Busca
<img src="https://i.imgur.com/8SBcrfc.png" width="30%"><br>
###Pagina do Livro
<img src="https://i.\imgur.com/bJ8kItt.png" width="30%"><br>
###Pagina do Livro
Com criticas
<img src="https://i.imgur.com/JmksKXF.png" width="30%"><br>
###Pagina do Usuario
<img src="https://i.imgur.com/MpfGWJy.png" width="30%"><br>
<img src="https://i.imgur.com/09Zkz7r.png" width="30%"><br>
###Ranking dos Livros
<img src="https://i.imgur.com/6dHJJEc.png" width="30%"><br>
###Pagina de Cupons
O primeiro cupom foi usado pelo usuario logado
<img src="https://i.imgur.com/Xh1F99w.png" width="30%"><br>
  
</details>
