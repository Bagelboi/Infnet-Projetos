Workflows são arquivos que definem um pipeline automatizado de processos que serão executados nos conteudos de um repositorio, detalhando regras de execução, quando são executados esses processos, quais branches é executado esses processos, etc. Actions são componentes configuraveis (tem actions que são mais configuraveis que outras) que um Workflow pode implementar em seu pipeline, especificamente nos steps de um job.

Actions podem ser repositorios de código empacotados ou imagens docker, e podem expor configurações de entrada e saida para o arquivo de Workflow definir.

(https://docs.github.com/en/actions/tutorials/create-actions/create-a-javascript-action)

No caso do checkstyle ele é uma ação que age como um wrapper para outras ações (reviewdog por exemplo) para fazer a analise estatica do código. No ci_tp2.yml ele faz é invocado com "uses" e a instrução logo abaixo "with" configura uma variavel de entrada que seria o token de GitHub para poder fussar o conteudo repositorio.