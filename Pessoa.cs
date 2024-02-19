class Pessoa {
    private string _nome;
    private int _idade;
    private int[] _cpf = {0,0,0,0};
    public string Nome {
        get { return _nome; }
        set { _nome = value; }
    }

    public int Idade {
        get { return _idade; }
        set { _idade = Math.Abs(value); }
    }

    public int[] CPF {
        get { return _cpf; }
        set {
            if (value.Length == 4) 
                _cpf = value;
        }
    }

    public override string ToString() {
        string cpfFormatado = $"{_cpf[0]:D3}.{_cpf[1]:D3}.{_cpf[2]:D3}-{_cpf[3]:D2}";
        return $"\nNome: {_nome}\nIdade: {_idade}\nCPF: {cpfFormatado}";
    }
}