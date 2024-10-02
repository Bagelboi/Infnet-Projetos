# Projetos da Infnet
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
  O projeto do bloco consistiu em criar um site full-stack que utilizava o Firebase para atuar como banco de dados e o Vue para gerenciar o MVC com a metodologia XP (Extreme Programming).
  A ideia foi um site no estilo de Goodreads, onde você seria recompensado pontos por criticar, comentar, etc sobre os livros e lojistas ofereceriam cupons para quem quisesse gastar esses pontos e ganhar promoções na compra de livros. 
  E por mais que não esta completo tudo tudo tudo, estou bem orgulhoso do resultado inicial. Devo credito pela minha dupla Lucas Dias de Gondra.
  
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

# Bloco de Interação Humano Computador
<details>
  <summary>Site de Atendimento Pediatrico Publico</summary><br>
  O projeto era um site do governo para gestão de atendimento pediatrico, usando as varias técnicas de IHC como visto no livro da Simone Barbosa. Talvez foi o projeto que mais me forço fora da minha zona de comforto no aspecto de que precisei entrevistar pessoas aleatórias na rua perto dos hospitais para ter uma ideia minima de como funcionava o sistema.<br>

  ## Documentação
  https://docs.google.com/document/d/1HvHB4ANvCppxleShT7f0dcxb3LtXXGDc4RMps80xtcw/edit?usp=sharing

  ## Protótipo Interativo
  https://www.figma.com/proto/2nAkfGsYK6MW4YBW0vrwR7/Sistema-de-Cadastro-da-Crian%C3%A7a-para-Atendimento-Pediatrico-em-Redes-Publicas

  ## Fotos
  <img src="https://i.imgur.com/A7nr9ZQ.png" width="56%"><br>
  <img src="https://i.imgur.com/JEEzYxx.png" width="56%"><br>
  <img src="https://i.imgur.com/DuBjmRX.png" width="56%"><br>
  <img src="https://i.imgur.com/stOecr9.png" width="56%"><br>
</details>

# Bloco de Java
<details>
  <summary>Sistema de Gestão Academica</summary><br>
  O projeto de bloco consistia em aprender á criar os varios diagramas da UML, como de caso de uso, de classe e de sequencia para depois implementar somente uma pequena parte em código com o Entity Framework do .NET. O projeto era um sistema de gestão academica replicando o modelo da INFNET.<br>
  
  ## Documento
  https://docs.google.com/document/d/10G8Tgg3mmFNvkUtC91dZfotngbWnkmIXxd_lXwV1kVw/edit?usp=sharing
  
  ## Fotos
  ### Diagrama de caso de uso
  <img src="https://i.imgur.com/FhamdS4.png" width="56%"><br>
  ### Diagrama de classe
  <img src="https://i.imgur.com/ygQsHCv.png" width="56%"><br>
  ### Diagrama de sequencia
  <img src="https://i.imgur.com/gAjwCPX.png" width="56%"><br>
  ### Código Principal
  <img src="https://i.imgur.com/AmIedUy.png" width="56%"><br>
  
</details>

<details>
  <summary>CRUD ficticio do exercito brasileiro</summary><br>
  Um CRUD simples feito com o SparkJava, possuindo um front-end de templates alem do back-end.<br><br>
  O código esta na ramificação: java_crud_exercito (https://github.com/Bagelboi/Infnet-Projetos/tree/java_crud_exercito)<br>
  
  ## Fotos
  <img src="https://i.imgur.com/Fwcm4GL.png" width="56%"><br>
  <img src="https://i.imgur.com/BKIri1l.png" width="56%"><br>
  <img src="https://i.imgur.com/CMbnHDW.png" width="56%"><br>
  <img src="https://i.imgur.com/2xn2wCy.png" width="56%"><br>
</details>

# Bloco de Python e SQL
<details>
  <summary>Sistema de Chamados de T.I</summary><br>
  O projeto de bloco consistia em criar um sistema de chamados baseado no GLPI e expor os dados como uma API para ser testada no Looker Studio com a projeção deles.<br>
  Código (https://replit.com/@DanielGomes58/SistemaChamadosPB) <br>
  Apresentação (https://www.youtube.com/watch?v=CfFDJi58FGU) <br>

  ## Fotos
  ### Relações de Entidades do Banco de Dados
  <img src="https://i.imgur.com/q6HgxKM.png"  width="56%"><br>
  ### Código rodando
  <img src="https://i.imgur.com/L0EzRgd.png"  width="56%"><br>
  ### Looker Studio
  <img src="https://i.imgur.com/q3eQGa2.png"  width="56%"><br>
  <img src="https://i.imgur.com/Q5iy4Dq.png"  width="56%"><br>
  <img src="https://i.imgur.com/YpMF1oZ.png"  width="56%"><br>
  
</details>
<details>
  <summary>Sistema de Cadastro de Pessoa Fisica</summary><br>
  Esse projeto inicialmente consistia em criar um menu simples de cadastro de pessoas fisicas. Depois se tornou uma API qual voce pode fazer requisições POST e GET para criar ou retornar registros de pessoas fisicas.<br>
  https://replit.com/@DanielGomes58/Servidor-Cadastro-API
  
  ## Fotos
  ### Versão Inicial
  <img src="https://i.imgur.com/SNTrBMa.png"  width="56%"><br>

  ### Forma de API
  <img src="https://i.imgur.com/fEr0BpM.png"  width="56%"><br>
  <img src="https://i.imgur.com/RFlGu8e.png"  width="56%"><br>
  
</details>

# Bloco de Software Escalaveis (DDD, Springboot, Docker, Kubernetes, CI/CD, RabbitMQ, Axon, Logging)
<details>
  <summary>Blog de Projetos de Alunos</summary><br>
  Esse projeto foi idealizado como um CRUD de projetos e os micro-serviços dentro desses. Foi criado um front-end com React e o back-end com varios microserviços feitos com Springboot + RabbitMQ + Papertrail e finalmente plantados no Docker. <br>
  
## Repositórios de Github
- https://github.com/Bagelboi/ProjetoDeBlocoEscalaveis - Configurações do docker-compose e módulos do projeto<br>
- https://github.com/DemarchiWorking/EurekaServer/tree/main - Servidor Eureka<br>
- https://github.com/Bagelboi/GatewayServicePB/tree/main - Serviço Gateway<br>
- https://github.com/DemarchiWorking/service-java-micro/tree/main - Serviço “Principal” e base de dados dos Services dos Projetos<br>
- https://github.com/DemarchiWorking/project-java-micro/tree/main - Serviço e base de dados dos Projetos<br>
- https://github.com/Bagelboi/Microservico-Comentarios-PB/tree/main - Serviço e base de dados de Comentarios nos Projetos<br>
- https://github.com/Bagelboi/alunoservice - Serviço e base de dados dos Alunos<br>

## Documento
https://docs.google.com/document/d/1HexdV6lmsqyBF55LbcLb755F42io3OKc9daZSnBZLTk/edit?usp=sharing

## Apresentação
https://www.youtube.com/watch?v=y-3Xjj4SoZQ

</details>

<details>
  <summary>DDD</summary><br>
  Essa matéria consistiu no aprendizado da metodologia de Domain Driven Design (DDD). Em geral, a matéria rodou em torno de um projeto de petshop ficticia chamada Petfriends.<br>

  ## Design Estratégico (Planejamento e Classificação dos Dominios do negócio)
  Documento (https://docs.google.com/document/d/1xVhFt8gZ5s2lKRlYX6i4EIqtPNjBulUYxQMV26h4iIo/edit?usp=sharing)
  ### Mapa de Contextos
  <img src="https://i.imgur.com/X2D91K0.png"  width="56%"><br>
  ### Diagrama de Classe dos Agregados
  <img src="https://i.imgur.com/KpP5sF4.png" width="56%"><br>

  ## Design Tatico (Implementação em Código)
  Foi considerado usar Axon para praticar Event Sourcing só que por questões de agilidade foi melhor usar o RabbitMQ para testar os eventos de dominio.<br>
  Repositório (https://github.com/Bagelboi/Infnet-Projetos/tree/sistema_petfriends)<br>
  Documento (https://docs.google.com/document/d/1FVwfioV6UeQcFcx8wt5n7lotFg6QZagaujIHaXSQiF8/edit?usp=sharing)<br>

</details>


