using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Security.Cryptography;
using System.Xml.Linq;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace at_cnet.Models
{
    public class Endereco
    {
        [Key]
        public int id { get; set; }

        [MaxLength(2)]
        [Required]
        public string? UF { get; set; }

        [DisplayName("Cidade")]
        public string? cidade { get; set; }

        [DisplayName("Bairro")]
        public string? bairro { get; set; }


        [DisplayName("Rua")]
        public string? rua { get; set; }

        [DisplayName("Numero")]
        public int? numero { get; set; }

        public override string ToString()
        {
            return $"{UF}, {cidade}, {bairro}, {rua}, {numero}";
        }
    }
}
