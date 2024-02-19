List<Pessoa> pessoas = new List<Pessoa>();

while(true) {
    Console.WriteLine("\n1) Adicionar Pessoas\n2) Mostrar Pessoas\n0) Sair");
    Console.Write("Escola uma opção: ");
    int opcao = int.Parse( Console.ReadLine() );
    switch(opcao) {
        case 0:
            return 0;
        case 1:
            Console.Write("\nDigite o nome: ");
            string nome = Console.ReadLine();

            int idade = -1;
            while(true) {
                Console.Write("Digite a idade: ");
                idade = int.Parse(Console.ReadLine());
                if (idade < 0) 
                    Console.WriteLine("Idade invalida!");
                else
                    break;

            }

            int[] cpf = new int[4];
            while(true) {
                Console.Write("Digite o CPF: ");
                string cpfInput = Console.ReadLine();
                if (cpfInput.Length != 11)
                    Console.WriteLine("CPF tem que ter 11 digitos!");
                else {
                    cpf[0] = Math.Abs(int.Parse( cpfInput.Substring(0, 3) ));
                    cpf[1] = Math.Abs(int.Parse( cpfInput.Substring(3, 3) ));
                    cpf[2] = Math.Abs(int.Parse( cpfInput.Substring(6, 3) ));
                    cpf[3] = Math.Abs(int.Parse( cpfInput.Substring(9, 2) ));
                    break;
                }
            }

            Pessoa new_p = new Pessoa();
            new_p.Nome = nome;
            new_p.Idade = idade;
            new_p.CPF = cpf;

            Console.WriteLine("Registrado com sucesso!\n" + new_p.ToString());
            pessoas.Add(new_p);
            break;
        case 2:
            if (pessoas.Count > 0) {
                foreach( Pessoa p in pessoas )
                    Console.WriteLine(p.ToString());
            } else
                Console.WriteLine("\nNão foi registrado ninguem ainda!");
            break;
    }

}